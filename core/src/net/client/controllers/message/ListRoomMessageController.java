package net.client.controllers.message;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.UpdateListRoomMessage;
import clientUI.RoomInfo;

import java.util.List;

public class ListRoomMessageController extends AbstractMessageController<List<RoomInfo>, Boolean> {

    private final UpdateListRoomMessage message;

    public ListRoomMessageController(TCPClient client, ClientApplication application) {
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
