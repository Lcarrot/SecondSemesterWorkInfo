package net.server;

import net.network.connection.TCPConnection;
import net.network.message.CreateRoomMessage;

import java.util.List;

public class Room {

    private int roomId;
    private String name;
    private int hostId;
    private int count;
    private List<TCPConnection> connections;

    public Room(int roomId,String name, int count, int hostId) {
        this.roomId = roomId;
        this.name = name;
        this.count = count;
        this.hostId = hostId;
    }

    public Room(int roomId, CreateRoomMessage message) {
        this.roomId = roomId;
        this.name = message.getName();
        this.hostId = message.getHostId();
        this.count = message.getCount();
    }

    public void connect(TCPConnection connection) {
        connections.add(connection);
    }
}
