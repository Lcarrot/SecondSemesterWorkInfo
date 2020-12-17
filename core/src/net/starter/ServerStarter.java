package net.starter;

import net.server.TCPServer;

public class ServerStarter {

    public static void main(String[] args) {
        TCPServer server = new TCPServer(Protocol.PORT, 5);
        server.start();
    }
}
