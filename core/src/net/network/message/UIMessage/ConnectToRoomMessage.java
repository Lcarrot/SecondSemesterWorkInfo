package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

import java.util.List;

public class ConnectToRoomMessage extends TCPMessage<RoomInfo> {

    private final int clientId;
    private final RoomInfo roomInfo;
    private boolean status = false;
    private List<Integer> otherPlayers;

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

    public List<Integer> getOtherPlayers() {
        return otherPlayers;
    }

    public void setOtherPlayers(List<Integer> otherPlayers) {
        this.otherPlayers = otherPlayers;
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
