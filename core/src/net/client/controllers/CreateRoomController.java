package net.client.controllers;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.CreateRoomMessage;

public class CreateRoomController extends AbstractController<CreateRoomMessage, RoomInfo>{

    public CreateRoomController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(CreateRoomMessage message) {
        if (message.isCreated()) {
            client.getRoomController().setRoomId(message.getRoomInfo().getRoomId());
            // TODO: 12/19/2020 открывать игру
        }
    }

    @Override
    public void send(RoomInfo message) {
        client.send(new CreateRoomMessage(client.getId(), message));
    }
}
