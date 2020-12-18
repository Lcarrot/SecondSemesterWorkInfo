package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

public class ConnectToRoomMessage extends TCPMessage<RoomInfo> {

    private final int id;
    private final RoomInfo roomInfo;
    private boolean status = false;

    public ConnectToRoomMessage(int id, RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        this.id = id;
    }

    @Override
    public RoomInfo getContent() {
        return roomInfo;
    }

    public int getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ConnectToRoomMessage{" +
                "id=" + id +
                ", roomInfo=" + roomInfo +
                ", status=" + status +
                '}';
    }
}
