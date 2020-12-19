package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

public class CreateRoomMessage extends TCPMessage<RoomInfo> {

    private final int clientId;
    private RoomInfo roomInfo;
    private boolean created = false;

    public CreateRoomMessage(int clientId, RoomInfo roomInfo) {
        this.clientId = clientId;
        this.roomInfo = roomInfo;
    }

    public int getClientId() {
        return clientId;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
    }

    @Override
    public String toString() {
        return "CreateRoomMessage{" +
                "id=" + clientId +
                ", roomInfo=" + roomInfo +
                ", created=" + created +
                '}';
    }
}
