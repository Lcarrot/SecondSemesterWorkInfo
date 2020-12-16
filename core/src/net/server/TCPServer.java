package net.server;

import net.network.connection.TCPConnection;
import net.network.message.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class TCPServer extends Server<TCPConnection, TCPMessage> {

    private final ThreadPoolExecutor executor;
    private CloseConnectionMessage closeConnectionMessage;
    private ConnectMessage connectMessage;
    private Set<Room> gameRoomSet;
    private int next_conn_id = 0;
    private int next_room_id = 0;
    private TCPReceiverMessage receiverMessage;

    public TCPServer(int port, int threadPoolSize) {
        super(port);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
        receiverMessage = new TCPReceiverMessage();
        connectMessage = new ConnectMessage();
        closeConnectionMessage = new CloseConnectionMessage();
    }

    @Override
    public void start() {
        super.start();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (isActive) {
                Socket socket = serverSocket.accept();
                openConnection(new TCPConnection(socket, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(TCPConnection connection) {
        connections.add(connection);
        connectMessage.setId(next_conn_id++);
        connection.send(connectMessage);
        executor.execute(connection);
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        connections.remove(connection);
        closeConnectionMessage.setId(connection.getId());
        connection.send(closeConnectionMessage);
        connection.close();
    }

    @Override
    public void receive(TCPMessage msg) {
        receiverMessage.handleMessage(msg);
    }

    private class TCPReceiverMessage {

        private void handleMessage(TCPMessage message) {
            if (message instanceof CloseConnectionMessage) {
                for (TCPConnection connection : connections) {
                    if (connection.getId() == ((CloseConnectionMessage) message).getId()) {
                        closeConnection(connection);
                        break;
                    }
                }
            }
            if (message instanceof CreateRoomMessage) {
                gameRoomSet.add(new Room(next_room_id++,(CreateRoomMessage) message));
            }
            if (message instanceof ChatMessage) {
                for (TCPConnection connection : connections) {
                    connection.send(message);
                }
            }
        }
    }
}
