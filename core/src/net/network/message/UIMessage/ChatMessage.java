package net.network.message.UIMessage;

import net.network.message.TCPMessage;

import java.io.Serializable;

public class ChatMessage extends TCPMessage<Serializable> {

    private final int clientId;
    private Serializable message;

    public ChatMessage(int clientId) {
        this.clientId = clientId;
    }

    public Serializable getMessage() {
        return message;
    }

    public void setMessage(Serializable message) {
        this.message = message;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + clientId +
                ", message='" + message + '\'' +
                '}';
    }
}
