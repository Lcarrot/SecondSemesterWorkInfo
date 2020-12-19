package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.ConnectToRoomMessage;
import clientUI.RoomInfo;

public class ConnectToRoomController extends AbstractController<ConnectToRoomMessage, RoomInfo> {

    public ConnectToRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(ConnectToRoomMessage message) {
        if (message.isStatus()) {
            client.getRoomController().setRoomId(message.getRoomInfo().getRoomId());
        }
        application.addPlayer(message);
    }

    @Override
    public void send(RoomInfo info) {
        ConnectToRoomMessage message = new ConnectToRoomMessage(client.getId(), info);
        client.send(message);
    }
}