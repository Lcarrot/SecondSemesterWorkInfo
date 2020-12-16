package net.network.message;

public class CloseConnectionMessage implements TCPMessage {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
