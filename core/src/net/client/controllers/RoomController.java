package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.connection.Receiver;
import net.network.connection.Sender;
import net.network.message.UIMessage.DoFragMessage;

public class RoomController implements Receiver<DoFragMessage>, Sender<Integer> {

    private final TCPClient client;
    private final ClientApplication clientApplication;
    private final DoFragMessage message;

    public RoomController(TCPClient client, ClientApplication clientApplication) {
        this.client = client;
        message = new DoFragMessage(client.getId());
        this.clientApplication = clientApplication;
    }

    public void setRoomId(Integer id) {
        message.setRoomId(id);
    }

    @Override
    public void receive(DoFragMessage message) {
        clientApplication.updateFrags(message.getId(), message.getKills());
    }

    @Override
    public void send(Integer integer) {
        message.setKills(integer);
        client.send(message);
    }
}
