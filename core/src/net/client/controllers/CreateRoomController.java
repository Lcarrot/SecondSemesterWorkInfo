package net.client.controllers;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.CreateRoomMessage;

public class CreateRoomController extends AbstractController<Boolean, RoomInfo>{

    public CreateRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(Boolean booleanValue) {
        if (booleanValue) {
            // TODO: 12/18/2020 начать игру
        }
    }

    @Override
    public void send(RoomInfo message) {
        client.send(new CreateRoomMessage(client.getId(), message));
    }
}
