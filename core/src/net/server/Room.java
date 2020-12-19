package net.server;

import clientUI.RoomInfo;
import net.network.connection.TCPConnection;
import net.network.message.UIMessage.CreateRoomMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Room {

    private final RoomInfo roomInfo;
    private List<TCPConnection> connections;

    public Room(int roomId, RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        this.roomInfo.setRoomId(roomId);
        connections = new LinkedList<>();
    }

    public Room(int roomId, CreateRoomMessage message) {
        roomInfo = message.getRoomInfo();
        this.roomInfo.setRoomId(roomId);
        connections = new LinkedList<>();
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
            roomInfo.addUser(connection.getId(), 0);
            return true;
        }
        return false;
    }

    public boolean disconnect(TCPConnection connection) {
        if (connections.size() > 0) {
            return connections.remove(connection);
        }
        return false;
    }

    public List<TCPConnection> getConnections() {
        return connections;
    }

    public void setKills(Integer id, Integer kills) {
        roomInfo.getMapUsers().put(id, kills);
    }
}
