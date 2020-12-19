package net.server;

import clientUI.RoomInfo;
import net.network.connection.TCPConnection;
import net.network.message.UIMessage.CreateRoomMessage;

import java.util.List;

public class Room {

    private final RoomInfo roomInfo;
    private List<TCPConnection> connections;

    public Room(int roomId, RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        this.roomInfo.setRoomId(roomId);
    }

    public Room(int roomId, CreateRoomMessage message) {
        roomInfo = message.getRoomInfo();
        this.roomInfo.setRoomId(roomId);
    }

    public String getName() {
        return roomInfo.getName();
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public int getRoomId() {
        return roomInfo.getRoomId();
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
