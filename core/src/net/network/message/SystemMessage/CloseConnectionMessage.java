package net.network.message.SystemMessage;

import net.network.message.TCPMessage;

public class CloseConnectionMessage extends TCPMessage<Integer> {


    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public Integer getContent() {
        return id;
    }

    @Override
    public String toString() {
        return "CloseConnectionMessage{" +
                "id=" + id +
                '}';
    }
}
