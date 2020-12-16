package net.client;

import net.network.connection.Sender;
import net.network.connection.TCPConnection;
import net.network.message.ChatMessage;
import net.network.message.CloseConnectionMessage;
import net.network.message.ConnectMessage;
import net.network.message.TCPMessage;

import java.net.Socket;

public class TCPClient extends AbstractTCPClient implements Sender<TCPMessage> {

    private TCPConnection connection;
    private GameClient gameClient;
    private ChatClient chatClient;
    private final TCPReceiverMessage tcpReceiverMessage;

    public TCPClient(Socket socket) {
        openConnection(new TCPConnection(socket, this));
        gameClient = new GameClient(this);
        tcpReceiverMessage = new TCPReceiverMessage();
    }

    @Override
    public void openConnection(TCPConnection connection) {
        this.connection = connection;
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        CloseConnectionMessage closeConnectionMessage = new CloseConnectionMessage();
        closeConnectionMessage.setId(connection.getId());
        connection.send(closeConnectionMessage);
        connection.close();
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {

    }

    @Override
    public void send(TCPMessage message) {
        connection.send(message);
    }

    @Override
    public void receive(TCPMessage message) {
        tcpReceiverMessage.handleMessage(message);
    }

    private void setChatClient(int id) {
        this.chatClient = new ChatClient(this, id);
    }

    private class TCPReceiverMessage {

        private void handleMessage(TCPMessage message) {
            if (message instanceof CloseConnectionMessage) {
                closeConnection(connection);
            }
            if (message instanceof ChatMessage) {
                chatClient.setMessage((ChatMessage) message);
            }
            if (message instanceof ConnectMessage) {
                connection.setId(((ConnectMessage) message).getId());
                setChatClient(((ConnectMessage) message).getId());
            }
        }
    }
}
