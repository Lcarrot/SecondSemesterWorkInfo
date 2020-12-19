package net.network.message.UIMessage;

import net.network.message.TCPMessage;

public class ChatStringMessage extends TCPMessage<String> {

    private final int clientId;
    private String message;

    public ChatStringMessage(int clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
