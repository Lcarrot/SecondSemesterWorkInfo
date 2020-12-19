package net.network.message.SystemMessage;

import net.network.message.TCPMessage;

public class CloseConnectionMessage extends TCPMessage<Integer> {


    private int clientId;

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "CloseConnectionMessage{" +
                "id=" + clientId +
                '}';
    }
}
