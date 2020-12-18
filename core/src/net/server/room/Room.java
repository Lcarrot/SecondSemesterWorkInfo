package net.server.room;

import clientUI.RoomInfo;
import net.network.connection.TCPConnection;
import net.network.message.UIMessage.CreateRoomMessage;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

    private RoomInfo roomInfo;
    private String name;
    private int roomId;
    private List<TCPConnection> connections;

    public Room(int roomId, RoomInfo roomInfo) {
        this.roomId  = roomId;
        this.roomInfo = roomInfo;
    }

    public Room(int roomId, CreateRoomMessage message) {
        this.roomId = roomId;
        roomInfo = message.getContent();
    }

    public String getName() {
        return name;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean connect(TCPConnection connection) {
        if (connections.size() < roomInfo.getCount()) {
            connections.add(connection);
            return true;
        }
        return false;
    }

    public void disconnect(TCPConnection connection) {
        connections.remove(connection);
    }

    public List<TCPConnection> getConnections() {
        return connections;
    }
}
