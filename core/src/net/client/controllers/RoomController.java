package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.connection.Receiver;
import net.network.connection.Sender;
import net.network.message.UIMessage.DoFragMessage;

public class RoomController implements Receiver<DoFragMessage>, Sender<Integer> {

    private final TCPClient client;
    private ClientApplication clientApplication;
    private final DoFragMessage message;

    public RoomController(TCPClient client, int roomId, ClientApplication clientApplication) {
        this.client = client;
        message = new DoFragMessage(client.getId(), roomId);
        this.clientApplication = clientApplication;
    }

    @Override
    public void receive(DoFragMessage message) {
        // TODO: 12/18/2020 сделать вывод фрагов
    }

    @Override
    public void send(Integer integer) {
        message.setKills(integer);
        client.send(message);
    }

}
