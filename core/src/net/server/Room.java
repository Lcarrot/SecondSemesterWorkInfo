package net.server;

import net.network.connection.TCPConnection;
import net.network.message.UIMessage.CreateRoomMessage;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

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

    public String getName() {
        return name;
    }

    public void connect(TCPConnection connection) {
        connections.add(connection);
    }
}
