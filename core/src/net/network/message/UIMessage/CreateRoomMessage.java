package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

public class CreateRoomMessage extends TCPMessage<RoomInfo> {

    private final int id;
    private final RoomInfo roomInfo;
    private boolean created = false;

    public CreateRoomMessage(int id, RoomInfo roomInfo) {
        this.id = id;
        this.roomInfo = roomInfo;
    }

    public int getId() {
        return id;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public RoomInfo getContent() {
        return roomInfo;
    }

    @Override
    public String toString() {
        return "CreateRoomMessage{" +
                "id=" + id +
                ", roomInfo=" + roomInfo +
                ", created=" + created +
                '}';
    }
}
