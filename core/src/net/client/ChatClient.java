package net.client;

import clientUI.ApplicationUI;
import net.network.message.UIMessage.ChatMessage;

import java.io.Serializable;

public class ChatClient {

    // TODO: 12/14/2020 добавить считывание из поля и отправка сообщения по нажатию
    private final TCPClient tcpClient;
    private ChatMessage message;
    private ApplicationUI applicationUI;

    public ChatClient(TCPClient tcpClient, int id, ApplicationUI applicationUI) {
        this.tcpClient = tcpClient;
        message = new ChatMessage(id);
        this.applicationUI = applicationUI;
    }

    public <T extends Serializable> void sendMessage(T obj) {
        message.setMessage(obj);
        tcpClient.send(message);
    }

    public void setMessage(ChatMessage message) {
        applicationUI.receivedMessage(message);
    }
}
