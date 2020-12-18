package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.ChatStringMessage;

public class ChatStringController extends AbstractController<ChatStringMessage, String> {

    private final ChatStringMessage chatStringMessage;

    public ChatStringController(TCPClient tcpClient, int id, ClientApplication application) {
        super(tcpClient, application);
        chatStringMessage = new ChatStringMessage(id);
    }

    public  void send(String obj) {
        chatStringMessage.setMessage(obj);
        client.send(chatStringMessage);
    }

    public void receive(ChatStringMessage message) {
        application.receivedMessage(message);
    }
}
