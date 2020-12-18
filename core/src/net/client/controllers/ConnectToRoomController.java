package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.ConnectToRoomMessage;
import clientUI.RoomInfo;

public class ConnectToRoomController extends AbstractController<Boolean, RoomInfo> {

    public ConnectToRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(Boolean message) {
        // TODO: 12/18/2020 присоединиться либо сказать, что присоединиться невозможно
    }

    @Override
    public void send(RoomInfo info) {
        ConnectToRoomMessage message = new ConnectToRoomMessage(client.getId(), info);
        client.send(message);
    }
}
