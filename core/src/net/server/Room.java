package net.server;

import net.network.message.CreateRoomMessage;

public class Room {

    private String name;
    private int hostId;
    private String password;
    private int count;

    public Room(String name, int count, int hostId, String password) {
        this.name = name;
        this.count = count;
        this.hostId = hostId;
        this.password = password;
    }

    public Room(CreateRoomMessage message) {
        this.name = message.getName();
        this.hostId = message.getHostId();
        this.count = message.getCount();
        this.password = message.getPassword();
    }
}
