package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

public class DisconnectFromRoomMessage extends TCPMessage<Integer> {

    private final int id;
    private final RoomInfo roomInfo;

    public DisconnectFromRoomMessage(int id, RoomInfo roomInfo) {
        this.id = id;
        this.roomInfo = roomInfo;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    @Override
    public Integer getContent() {
        return id;
    }

    @Override
    public String toString() {
        return "DisconnectFromRoomMessage{" +
                "id=" + id +
                ", roomInfo=" + roomInfo +
                '}';
    }
}
