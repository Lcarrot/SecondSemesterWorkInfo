package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

import java.util.Collection;
import java.util.List;

public class UpdateListRoomMessage extends TCPMessage<Collection<RoomInfo>> {

    private List<RoomInfo> rooms;
    private int clientId;

    public UpdateListRoomMessage(int clientId) {
        this.clientId = clientId;
    }

    public void setRooms(List<RoomInfo> roomSet) {
        rooms = roomSet;
    }

    public void clear() {
        rooms = null;
    }

    public List<RoomInfo> getRooms() {
        return rooms;
    }

    public int getClientId() {
        return clientId;
    }

    public boolean getStatus() {
        return rooms == null;
    }

    @Override
    public String toString() {
        return "UpdateListRoomMessage{" +
                "rooms=" + rooms +
                ", id=" + clientId +
                '}';
    }
}
