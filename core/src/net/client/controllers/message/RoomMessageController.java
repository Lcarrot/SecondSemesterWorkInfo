package net.client.controllers.message;

import clientUI.ClientApplication;
import clientUI.RoomInfo;
import net.client.TCPClient;
import net.network.message.UIMessage.DoFragMessage;

public class RoomMessageController extends AbstractMessageController<DoFragMessage, Integer> {

    private final DoFragMessage message;
    private RoomInfo roomInfo;

    public RoomMessageController(TCPClient client, ClientApplication clientApplication) {
        super(client, clientApplication);
        message = new DoFragMessage(client.getId());
    }

    public void setRoom(RoomInfo info) {
        roomInfo = info;
        message.setRoomId(info.getRoomId());
    }

    @Override
    public void receive(DoFragMessage message) {
        application.updateFrags(message.getId(), message.getKills());
    }

    @Override
    public void send(Integer integer) {
        message.setKills(integer);
        client.send(message);
    }
}
