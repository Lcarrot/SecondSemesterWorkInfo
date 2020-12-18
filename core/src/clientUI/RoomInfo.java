package clientUI;

import java.io.Serializable;

public class RoomInfo implements Serializable {

    private String name;
    private int hostId;
    private int count;
    private int roomId;

    public RoomInfo(String name, int hostId, int count) {
        this.name = name;
        this.hostId = hostId;
        this.count = count;
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
}
