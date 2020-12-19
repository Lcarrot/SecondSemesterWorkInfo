package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.UpdateListRoomMessage;
import clientUI.RoomInfo;

import java.util.List;

public class ListRoomController extends AbstractController<List<RoomInfo>, Boolean> {

    private final UpdateListRoomMessage message;

    public ListRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
        message = new UpdateListRoomMessage(client.getId());
    }

    @Override
    public void receive(List<RoomInfo> message) {
        application.receivedUpdateListRooms(message);
    }

    @Override
    public void send(Boolean message) {
        client.send(this.message);
    }

}
