package net.network.message.SystemMessage;

import net.network.message.TCPMessage;

public class ConnectMessage implements TCPMessage {

    private int id;

    public ConnectMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
