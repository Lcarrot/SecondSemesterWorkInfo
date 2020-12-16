package net.server;

import net.network.ConnectionListener;
import net.network.connection.Connection;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Server<L extends Connection<?>,T> implements ConnectionListener<L,T> {

    protected final int port;
    protected final Set<L> connections;
    protected boolean isActive;

    public Server(int port) {
        this.port = port;
        connections = new LinkedHashSet<>();
    }

    public void start() {
        isActive = true;
    }

    public void close() {
        isActive = false;
    }
}
