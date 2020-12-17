package net.network.message.UIMessage;

import net.network.message.TCPMessage;

import java.util.Date;

public class ChatMessage implements TCPMessage {

    private final int id;
    private Object message;
    private Date messageTime;

    public ChatMessage(int id) {
        this.id = id;
    }

    public Object getMessage() {
        return message;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public int getId() {
        return id;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }
}
