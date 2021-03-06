package net.client;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.network.connection.TCPConnection;
import net.network.message.*;
import net.network.message.SystemMessage.CloseConnectionMessage;
import net.network.message.UIMessage.*;

import java.io.Serializable;
import java.net.Socket;

public class GameTCPClient extends TCPClient {

    private final TCPReceiverMessage tcpReceiverMessage;

    public GameTCPClient(Socket socket, ClientApplication applicationUI) {
        super(socket, applicationUI);
        tcpReceiverMessage = new TCPReceiverMessage();
    }

    @Override
    public void openConnection(TCPConnection connection) {
        this.connection = connection;
        id = connection.getId();
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
        System.out.println((connection.toString() + " was added"));
    }

    public int getId() {
        return id;
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        CloseConnectionMessage closeConnectionMessage = new CloseConnectionMessage();
        connection.send(closeConnectionMessage);
        connection.close();
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {
        throw new RuntimeException(exception.getMessage(), exception);
    }

    public void close() {
        closeConnection(connection);
    }

    @Override
    public void receive(TCPMessage message) {
        System.out.println(("Catch message " + message.toString()));
        tcpReceiverMessage.handleMessage(message);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void addPlayerFrag(Integer killsCount) {
        roomMessageController.send(killsCount);
    }

    @Override
    public void updateListOfRooms() {
        listRoomController.send(true);
    }

    @Override
    public void createNewRoom(RoomInfo roomInfo) {
        createRoomController.send(roomInfo);
    }

    @Override
    public <T extends Serializable> void sendChatMessage(T obj) {
        chatStringController.send(obj);
    }

    @Override
    public void setNewRoom(RoomInfo roomInfo) {
        roomMessageController.setRoom(roomInfo);
    }

    @Override
    public void connectToRoom(RoomInfo roomInfo) {
        connectToRoomController.send(roomInfo);
    }

    @Override
    public void disconnectFromRoom(RoomInfo roomInfo) {
        disconnectFromRoomController.send(roomInfo);
    }

    @Override
    public void send(TCPMessage message) {
        connection.send(message);
    }

    private class TCPReceiverMessage {

        private void handleMessage(TCPMessage message) {
            if (message instanceof CloseConnectionMessage) closeConnection(connection);
            else if (message instanceof ChatMessage) chatStringController.receive((ChatMessage) message);
            else if (message instanceof UpdateListRoomMessage)  listRoomController.receive(((UpdateListRoomMessage) message).getRooms());
            else if (message instanceof ConnectToRoomMessage) connectToRoomController.receive(((ConnectToRoomMessage) message));
            else if (message instanceof CreateRoomMessage) createRoomController.receive(((CreateRoomMessage) message));
            else if (message instanceof DoFragMessage) roomMessageController.receive((DoFragMessage) message);
            else if (message instanceof DisconnectFromRoomMessage) disconnectFromRoomController.receive((DisconnectFromRoomMessage) message);
        }
    }
}
