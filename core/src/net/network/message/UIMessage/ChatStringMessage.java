package net.network.message.UIMessage;

import net.network.message.TCPMessage;

public class ChatStringMessage extends TCPMessage<String> {

    private int id;
    private String message;

    public ChatStringMessage() {
    }

    public String getMessage() {
        return message;
    }

    public ChatStringMessage(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getContent() {
        return message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
