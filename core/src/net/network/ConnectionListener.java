package net.network;

import net.network.connection.Connection;
import net.network.connection.Receiver;

public interface ConnectionListener<T extends Connection<?>, L> extends Receiver<L> {

    void openConnection(T connection);
    void closeConnection(T connection);
    void connectException(T connection, Exception exception);
}
