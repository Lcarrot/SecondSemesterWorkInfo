package clientUI;

import java.io.Serializable;
import java.util.Map;

public class RoomInfo implements Serializable {

    private final String name;
    private final int hostId;
    private final int count;
    private int roomId;
    private Map mapUsers;

    public RoomInfo(String name, int hostId, int count, Map mapUsers) {
        this.name = name;
        this.hostId = hostId;
        this.count = count;
        this.mapUsers = mapUsers;
    }



    public int getRoomId() {
        return roomId;
    }
    public String getName() {
        return name;
    }

    public int getHostId() {
        return hostId;
    }

    public int getCount() {
        return count;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Map getMapUsers() {
        return mapUsers;
    }

    @Override
    public String toString() {
        return "name= " + name + ", roomId= " + roomId;
    }
}
