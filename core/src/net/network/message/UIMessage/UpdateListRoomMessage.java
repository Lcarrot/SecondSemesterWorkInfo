package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import clientUI.RoomInfo;

import java.util.Collection;
import java.util.List;

public class UpdateListRoomMessage extends TCPMessage<Collection<RoomInfo>> {

    private List<RoomInfo> rooms;
    private int id;

    public UpdateListRoomMessage(int id) {
        this.id = id;
    }

    public void setRooms(List<RoomInfo> roomSet) {
        rooms = roomSet;
    }

    public void clear() {
        rooms = null;
    }
    @Override
    public List<RoomInfo> getContent() {
        return rooms;
    }

    public int getId() {
        return id;
    }

    public boolean getStatus() {
        return rooms == null;
    }

    @Override
    public String toString() {
        return "UpdateListRoomMessage{" +
                "rooms=" + rooms +
                ", id=" + id +
                '}';
    }
}
