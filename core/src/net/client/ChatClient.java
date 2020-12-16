package net.client;

import net.network.connection.Sender;
import net.network.message.ChatMessage;

public class ChatClient implements Sender<ChatMessage> {

    // TODO: 12/14/2020 добавить считывание из поля и отправка сообщения по нажатию
    private final TCPClient tcpClient;
    private ChatMessage message;

    public ChatClient(TCPClient tcpClient, int id) {
        this.tcpClient = tcpClient;
        message = new ChatMessage(id);
    }

    @Override
    public void send(ChatMessage message) {
        tcpClient.send(message);
    }

    public void setMessage(ChatMessage message) {

    }
}
