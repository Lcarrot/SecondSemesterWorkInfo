package net.network.connection;


public interface Connection<T> {

    boolean isAlive();
    void close();
}
