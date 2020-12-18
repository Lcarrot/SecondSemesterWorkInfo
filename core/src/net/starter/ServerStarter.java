package net.starter;

import net.server.TCPServer;

public class ServerStarter {

    public static void main(String[] args) {
        System.out.println("started");
        TCPServer server = new TCPServer(Protocol.PORT, 25);
        server.start();
    }
}
