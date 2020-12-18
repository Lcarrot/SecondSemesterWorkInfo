package net.server;

import net.network.connection.TCPConnection;
import net.network.message.*;
import net.network.message.SystemMessage.CloseConnectionMessage;
import net.network.message.UIMessage.*;
import net.server.room.Room;

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
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (isActive) {
                Socket socket = serverSocket.accept();
                TCPConnection connection = new TCPConnection(socket, this, next_conn_id++);
                openConnection(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(TCPConnection connection) {
        connections.add(connection);
        executor.execute(connection);
        System.out.println("connection was added" + next_conn_id);
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        connections.remove(connection);
        connection.send(closeConnectionMessage);
        connection.close();
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {

    }

    @Override
    public void receive(TCPMessage msg) {
        receiverMessage.handleMessage(msg);
    }

    private class TCPReceiverMessage {

        private void handleMessage(TCPMessage message) {
            if (message instanceof CloseConnectionMessage) {
                connections.stream().filter(x -> x.getId() == ((CloseConnectionMessage) message).getId())
                        .findFirst().ifPresent(connections::remove);
            } else if (message instanceof CreateRoomMessage) {
                gameRoomSet.add(new Room(next_room_id++, (CreateRoomMessage) message));
                connections.stream().filter(x -> x.getId() == ((CreateRoomMessage) message)
                        .getContent().getHostId()).findFirst().ifPresent(x -> {
                    ((CreateRoomMessage) message).setCreated(true);
                    x.send(message);
                });
            } else if (message instanceof ChatStringMessage) {
                broadcastSendMessage(connections, message);
            } else if (message instanceof UpdateListRoomMessage) {
                ((UpdateListRoomMessage) message).setRooms(gameRoomSet.stream().map(Room::getRoomInfo).collect(Collectors.toList()));
                connections.stream().filter(x -> x.getId() == ((UpdateListRoomMessage) message).getId()).findFirst().ifPresent(connection -> {
                    ((UpdateListRoomMessage) message).setRooms(gameRoomSet.stream().map(Room::getRoomInfo).collect(Collectors.toList()));
                    connection.send(message);
                });
            } else if (message instanceof DoFragMessage) {
                gameRoomSet.stream().filter(x -> x.getRoomId() == ((DoFragMessage) message).getId()).
                        findAny().ifPresent(value -> broadcastSendMessage(value.getConnections(), message));
            } else if (message instanceof ConnectToRoomMessage) {
                gameRoomSet.stream().filter(room -> room.getRoomId() == ((ConnectToRoomMessage) message).getContent()
                        .getRoomId()).findFirst().ifPresent(room -> connections.stream().
                        filter(connection -> connection.getId() == ((ConnectToRoomMessage) message).getId()).
                        findFirst().ifPresent(connection -> {
                    ((ConnectToRoomMessage) message).setStatus(room.connect(connection));
                    connection.send(message);
                }));
            } else if (message instanceof DisconnectFromRoomMessage) {
                gameRoomSet.stream().filter(room -> room.getRoomId() == ((DisconnectFromRoomMessage) message).getRoomInfo()
                        .getRoomId()).findFirst().ifPresent(room -> connections.stream()
                        .filter(connection -> connection.getId() == ((DisconnectFromRoomMessage) message).getContent())
                        .findFirst().ifPresent(room::disconnect));
            }
        }

        private void broadcastSendMessage(Collection<TCPConnection> connections, TCPMessage message) {
            connections.forEach(x -> x.send(message));
        }
    }
}

