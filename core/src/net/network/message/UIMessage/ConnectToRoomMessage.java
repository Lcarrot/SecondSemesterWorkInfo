package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

import java.util.List;

public class ConnectToRoomMessage extends TCPMessage<RoomInfo> {

    private final int clientId;
    private RoomInfo roomInfo;
    private boolean status = false;

    public ConnectToRoomMessage(int clientId, RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        this.clientId = clientId;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public int getClientId() {
        return clientId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
    }

    @Override
    public String toString() {
        return "ConnectToRoomMessage{" +
                "id=" + clientId +
                ", roomInfo=" + roomInfo +
                ", status=" + status +
                '}';
    }
}
