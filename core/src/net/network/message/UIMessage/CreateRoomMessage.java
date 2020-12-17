package net.network.message.UIMessage;

import net.network.message.TCPMessage;

public class CreateRoomMessage implements TCPMessage {

    private final String name;
    private final String password;
    private final int count;
    private final int hostId;

    public CreateRoomMessage(String name, String password, int count, int hostId) {
        this.name = name;
        this.password = password;
        this.count = count;
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getCount() {
        return count;
    }

    public int getHostId() {
        return hostId;
    }
}
