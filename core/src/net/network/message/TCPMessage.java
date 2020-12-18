package net.network.message;

import java.io.Serializable;

public abstract class TCPMessage<T> implements Serializable {

    public abstract T getContent();
}
