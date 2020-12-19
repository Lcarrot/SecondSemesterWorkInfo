package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

public class DisconnectFromRoomMessage extends TCPMessage<Integer> {

    private final int clientId;
    private final RoomInfo roomInfo;

    public DisconnectFromRoomMessage(int clientId, RoomInfo roomInfo) {
        this.clientId = clientId;
        this.roomInfo = roomInfo;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "DisconnectFromRoomMessage{" +
                "id=" + clientId +
                ", roomInfo=" + roomInfo +
                '}';
    }
}
