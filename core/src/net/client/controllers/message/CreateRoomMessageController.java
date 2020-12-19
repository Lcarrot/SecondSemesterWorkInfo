package net.client.controllers.message;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.CreateRoomMessage;

public class CreateRoomMessageController extends AbstractMessageController<CreateRoomMessage, RoomInfo> {

    public CreateRoomMessageController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(CreateRoomMessage message) {
        if (message.isCreated()) {
            client.setNewRoom(message.getRoomInfo());
        }
        application.joinGame(message.isCreated(), message.getRoomInfo());
    }

    @Override
    public void send(RoomInfo message) {
        client.send(new CreateRoomMessage(client.getId(), message));
    }
}
