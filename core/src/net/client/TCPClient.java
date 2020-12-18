package net.client;

import clientUI.ClientApplication;
import net.client.controllers.*;
import net.network.ConnectionListener;
import net.network.connection.Sender;
import net.network.connection.TCPConnection;
import net.network.message.TCPMessage;

import java.net.Socket;

public abstract class TCPClient implements ConnectionListener<TCPConnection, TCPMessage>, Sender<TCPMessage> {

    protected TCPConnection connection;
    protected final ChatStringController chatStringController;
    protected final ListRoomController listRoomController;
    protected RoomController roomController;
    protected final ConnectToRoomController connectToRoomController;
    protected final CreateRoomController createRoomController;
    protected int id;

    public TCPClient(Socket socket, ClientApplication applicationUI) {
        openConnection(new TCPConnection(socket, this));
        chatStringController = new ChatStringController(this, id, applicationUI);
        listRoomController = new ListRoomController(this, applicationUI);
        connectToRoomController = new ConnectToRoomController(this, applicationUI);
        createRoomController = new CreateRoomController(this, applicationUI);
    }

    public int getId() {
        return id;
    }

    public ChatStringController getChatController() {
        return chatStringController;
    }

    public ConnectToRoomController getConnectToRoomController() {return connectToRoomController;}

    public CreateRoomController getCreateRoomController() { return createRoomController;}

    public ListRoomController getListRoomController() {return listRoomController;}

    public RoomController getRoomController() {return roomController;}

    public void send(TCPMessage message) {
        connection.send(message);
    }
}
