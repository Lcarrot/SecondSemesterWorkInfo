package net.client.controllers.message;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.ConnectToRoomMessage;
import clientUI.RoomInfo;

public class ConnectToRoomMessageController extends AbstractMessageController<ConnectToRoomMessage, RoomInfo> {

    public ConnectToRoomMessageController(TCPClient client, ClientApplication application) {
        super(client, application);
    }

    @Override
    public void receive(ConnectToRoomMessage message) {
        if (message.isStatus()) {
            client.setNewRoom(message.getRoomInfo());
            if (message.getClientId() == client.getId()) {
                application.hideApplication();
                application.joinGame(message.isStatus(), message.getRoomInfo());
            }
            else {
                application.addPlayer(message.isStatus(), message.getRoomInfo());
            }
        }
        else {
            client.updateListOfRooms();
        }
    }

    @Override
    public void send(RoomInfo info) {
        client.send(new ConnectToRoomMessage(client.getId(), info));
    }
}
