package net.network.message.UIMessage;

import net.network.message.TCPMessage;

public class DoFragMessage extends TCPMessage<Integer> {

    private final int id;
    private final int roomId;
    private int kills;

    public DoFragMessage(int id, int roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    @Override
    public Integer getContent() {
        return kills;
    }

    public int getId() {
        return id;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public int getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "DoFragMessage{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", kills=" + kills +
                '}';
    }
}
