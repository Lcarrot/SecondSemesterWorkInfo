package net.client.controllers.message;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.DisconnectFromRoomMessage;

public class DisconnectFromRoomMessageController extends AbstractMessageController<DisconnectFromRoomMessage,RoomInfo> {

    private DisconnectFromRoomMessage message;

    public DisconnectFromRoomMessageController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(DisconnectFromRoomMessage message) {
        application.playerIsDisconnected(message.getRoomInfo());
    }

    @Override
    public void send(RoomInfo roomInfo) {
        client.send(new DisconnectFromRoomMessage(client.getId(), roomInfo));
    }
}
