package net.network.message.UIMessage;

import net.network.message.TCPMessage;
import net.server.Room;

import java.util.Set;

public class UpdateListRoomMessage implements TCPMessage {

    private Room[] rooms;
    private final int id;

    public UpdateListRoomMessage(int id) {
        this.id = id;
    }

    public void setRooms(int counts, Set<Room> roomSet) {
        rooms = roomSet.toArray(new Room[counts]);
    }

    public void clear() {
        rooms = null;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public String[] getNamesRooms() {
        String[] names = new String[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            names[i] = rooms[i].getName();
        }
        return names;
    }

    public int getId() {
        return id;
    }

    public boolean getStatus() {
        return rooms == null;
    }
}
