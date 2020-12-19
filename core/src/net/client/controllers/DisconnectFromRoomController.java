package net.client.controllers;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.DisconnectFromRoomMessage;

public class DisconnectFromRoomController extends AbstractController<DisconnectFromRoomMessage,RoomInfo> {

    private DisconnectFromRoomMessage message;

    public DisconnectFromRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(DisconnectFromRoomMessage message) {
        // TODO: 12/19/2020 удалить игрока из таблички
    }

    @Override
    public void send(RoomInfo roomInfo) {
        client.send(new DisconnectFromRoomMessage(client.getId(), roomInfo));
    }
}
