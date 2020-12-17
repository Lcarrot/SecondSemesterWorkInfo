package net.network.message.SystemMessage;

import net.network.message.TCPMessage;

public class CloseConnectionMessage implements TCPMessage {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
