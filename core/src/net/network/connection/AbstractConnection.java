package net.network.connection;

import net.network.ConnectionListener;

import java.net.Socket;

public abstract class AbstractConnection<T, L extends Connection<?>> implements Connection<T> {

    protected boolean isAlive;
    protected ConnectionListener<L, T> listener;

    public abstract boolean isAlive();

    public abstract void close();
}
