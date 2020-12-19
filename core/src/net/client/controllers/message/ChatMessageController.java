package net.client.controllers.message;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.message.UIMessage.ChatMessage;

import java.io.Serializable;

public class ChatMessageController extends AbstractMessageController<ChatMessage, Serializable> {

    private final ChatMessage chatMessage;

    public ChatMessageController(TCPClient tcpClient, int id, ClientApplication application) {
        super(tcpClient, application);
        chatMessage = new ChatMessage(id);
    }

    public  void send(Serializable obj) {
        chatMessage.setMessage(obj);
        client.send(chatMessage);
    }

    public void receive(ChatMessage message) {
        application.receivedMessage(message);
    }
}
