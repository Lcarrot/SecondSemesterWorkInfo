package net.server;

import net.network.connection.TCPConnection;
import net.network.message.*;
import net.network.message.SystemMessage.CloseConnectionMessage;
import net.network.message.UIMessage.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class TCPServer extends Server<TCPConnection, TCPMessage> {

    private final ThreadPoolExecutor executor;
    private final CloseConnectionMessage closeConnectionMessage;
    private final Set<Room> gameRoomSet;
    private int next_conn_id = 0;
    private int next_room_id = 0;
    private final TCPReceiverMessage receiverMessage;

    public TCPServer(int port, int threadPoolSize) {
        super(port);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
        receiverMessage = new TCPReceiverMessage();
        closeConnectionMessage = new CloseConnectionMessage();
        gameRoomSet = new LinkedHashSet<>();
    }

    @Override
    public void start() {
        super.start();
        TCPConnection connection = null;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (isActive) {
                Socket socket = serverSocket.accept();
                connection = new TCPConnection(socket, this, next_conn_id++);
                openConnection(connection);
            }
        } catch (IOException e) {
            assert connection != null;
            connectException(connection, e);
        }
    }

    @Override
    public void openConnection(TCPConnection connection) {
        connections.add(connection);
        executor.execute(connection);
        System.out.println((connection.toString() + " was added"));
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        connections.remove(connection);
        connection.close();
        System.out.println((connection.toString() + " was closed"));
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {
        throw new RuntimeException(connection.toString() + "throw exception ",exception);
    }

    @Override
    public void receive(TCPMessage msg) {
        receiverMessage.handleMessage(msg);
    }

    private class TCPReceiverMessage {

        private void handleMessage(TCPMessage message) {
            if (message instanceof CloseConnectionMessage) {
                getConnectionById(((CloseConnectionMessage) message).getClientId()).ifPresent(TCPServer.this::closeConnection);
            } else if (message instanceof CreateRoomMessage) {
                gameRoomSet.add(new Room(next_room_id++, (CreateRoomMessage) message));
                getConnectionById(((CreateRoomMessage) message).getClientId()).ifPresent(connection -> {
                    ((CreateRoomMessage) message).setCreated(true);
                    connection.send(message);
                });
            } else if (message instanceof ChatStringMessage) {
                broadcastSendMessage(connections, message);
            } else if (message instanceof UpdateListRoomMessage) {
                ((UpdateListRoomMessage) message).setRooms(gameRoomSet.stream().map(Room::getRoomInfo).collect(Collectors.toList()));
                getConnectionById(((UpdateListRoomMessage) message).getClientId()).ifPresent(
                        connection -> {
                            ((UpdateListRoomMessage) message).setRooms(gameRoomSet.stream().map(Room::getRoomInfo).collect(Collectors.toList()));
                            connection.send(message);
                        });
            } else if (message instanceof DoFragMessage) {
                getRoomById(((DoFragMessage) message).getRoomId()).ifPresent(
                        room -> broadcastSendMessage(room.getConnections(), message));
            } else if (message instanceof ConnectToRoomMessage) {
                getRoomById(((ConnectToRoomMessage) message).getRoomInfo().getRoomId()).ifPresent
                        (room -> getConnectionById(((ConnectToRoomMessage) message).getClientId()).ifPresent
                                (connection -> {
                                    ((ConnectToRoomMessage) message).setStatus(room.connect(connection));
                                    connection.send(message);
                                }));
            } else if (message instanceof DisconnectFromRoomMessage) {
                getRoomById(((DisconnectFromRoomMessage) message).getRoomInfo().getRoomId())
                        .ifPresent(room -> getConnectionById(((DisconnectFromRoomMessage) message)
                                .getClientId()).ifPresent(room::disconnect));
            }
        }

        private void broadcastSendMessage(Collection<TCPConnection> connections, TCPMessage message) {
            connections.forEach(x -> x.send(message));
        }

        private Optional<Room> getRoomById(int id) {
            return gameRoomSet.stream()
                    .filter(room -> room.getRoomId() == id).findAny();
        }

        private Optional<TCPConnection> getConnectionById(int id) {
            return connections.stream()
                    .filter(connection -> connection.getId() == (id))
                    .findAny();
        }
    }
}

