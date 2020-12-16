package net.network.connection;

import net.network.ConnectionListener;

public abstract class AbstractConnection<T, L extends Connection<?>> implements Connection<T> {

    protected boolean isAlive;
    protected ConnectionListener<L, T> listener;

    public boolean isAlive() {
        return isAlive;
    }

    public void close() {
        isAlive = false;
    }
}
