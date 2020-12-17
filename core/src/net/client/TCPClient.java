package net.client;

import clientUI.RoomsController;
import clientUI.listeners.RoomsListener;
import net.network.connection.TCPConnection;
import net.network.message.*;
import net.network.message.SystemMessage.CloseConnectionMessage;
import net.network.message.SystemMessage.ConnectMessage;
import net.network.message.UIMessage.ChatMessage;
import net.network.message.UIMessage.UpdateListRoomMessage;
import net.server.Room;

import java.io.Serializable;
import java.net.Socket;

public class TCPClient extends AbstractTCPClient {

    private TCPConnection connection;
    private GameClient gameClient;
    private RoomsController roomsController;
    private ChatClient chatClient;
    private final TCPReceiverMessage tcpReceiverMessage;
    private UpdateListRoomMessage updateListRoomMessage;

    public TCPClient(Socket socket) {
        tcpReceiverMessage = new TCPReceiverMessage();
        openConnection(new TCPConnection(socket, this));
        gameClient = new GameClient(this);
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

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void send(TCPMessage message) {
        connection.send(message);
    }

    @Override
    public void receive(TCPMessage message) {
        tcpReceiverMessage.handleMessage(message);
    }

    private void setId(int id) {
        this.chatClient = new ChatClient(this, id);
        updateListRoomMessage = new UpdateListRoomMessage(id);
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
                setId(((ConnectMessage) message).getId());
            }
            if (message instanceof UpdateListRoomMessage) {
                updateListRoomMessage = (UpdateListRoomMessage) message;
            }
        }
    }
}
