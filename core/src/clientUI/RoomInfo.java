package clientUI;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomInfo implements Serializable {

    private final String name;
    private final int hostId;
    private final int count;
    private int roomId;
    private final Map<Integer, Integer> mapUsers;

    public RoomInfo(String name, int hostId, int count) {
        this.name = name;
        this.hostId = hostId;
        this.count = count;
        mapUsers = new LinkedHashMap<>();
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

    public Map<Integer, Integer> getMapUsers() {
        return mapUsers;
    }

    public boolean addUser(Integer id, Integer kills) {
        if (mapUsers.size() < count) {
            mapUsers.put(id, kills);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name room: " + name + ", quantity users: " + mapUsers.size() + ",   capacity: " + count;
    }
}
