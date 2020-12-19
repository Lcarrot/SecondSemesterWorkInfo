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

    public TCPClient(Socket socket, ClientApplication application) {
        openConnection(new TCPConnection(socket, this));
        chatStringController = new ChatStringController(this, id, application);
        listRoomController = new ListRoomController(this, application);
        connectToRoomController = new ConnectToRoomController(this, application);
        createRoomController = new CreateRoomController(this, application);
        roomController = new RoomController(this, application);
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
