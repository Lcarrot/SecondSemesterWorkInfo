package net.client;

import net.network.message.UIMessage.ChatMessage;

import java.io.Serializable;

public class ChatClient {

    // TODO: 12/14/2020 добавить считывание из поля и отправка сообщения по нажатию
    private final TCPClient tcpClient;
    private ChatMessage message;

    public ChatClient(TCPClient tcpClient, int id) {
        this.tcpClient = tcpClient;
        message = new ChatMessage(id);
    }

    public <T extends Serializable> void sendMessage(T obj) {
        message.setMessage(obj);
        tcpClient.send(message);
    }

    public void setMessage(ChatMessage message) {

    }
}
