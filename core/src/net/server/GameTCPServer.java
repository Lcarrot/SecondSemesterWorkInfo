package net.server;

import net.network.connection.TCPConnection;

public class GameTCPServer extends TCPServer {

    public GameTCPServer(int port, int threadPoolSize) {
        super(port, threadPoolSize);
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {

    }
}
