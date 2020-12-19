package net.client;

import clientUI.ClientApplication;
import net.client.controllers.ConnectionController;
import net.client.controllers.message.*;
import net.network.ConnectionListener;
import net.network.connection.Sender;
import net.network.connection.TCPConnection;
import net.network.message.TCPMessage;

import java.net.Socket;

public abstract class TCPClient implements ConnectionListener<TCPConnection, TCPMessage>, Sender<TCPMessage>, ConnectionController {

    protected TCPConnection connection;
    protected final ChatMessageController chatStringController;
    protected final ListRoomMessageController listRoomController;
    protected RoomMessageController roomMessageController;
    protected final ConnectToRoomMessageController connectToRoomController;
    protected final CreateRoomMessageController createRoomController;
    protected final DisconnectFromRoomMessageController disconnectFromRoomController;
    protected int id;

    public TCPClient(Socket socket, ClientApplication application)  {
        openConnection(new TCPConnection(socket, this));
        chatStringController = new ChatMessageController(this, id, application);
        listRoomController = new ListRoomMessageController(this, application);
        connectToRoomController = new ConnectToRoomMessageController(this, application);
        createRoomController = new CreateRoomMessageController(this, application);
        roomMessageController = new RoomMessageController(this, application);
        disconnectFromRoomController = new DisconnectFromRoomMessageController(this, application);
    }

    public int getId() {
        return id;
    }
}
