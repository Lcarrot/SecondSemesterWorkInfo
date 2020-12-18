package net.client.controllers;

import clientUI.ClientApplication;
import net.client.TCPClient;
import net.network.connection.Receiver;
import net.network.connection.Sender;

public abstract class AbstractController<T,P> implements Receiver<T>, Sender<P> {

    protected TCPClient client;
    protected ClientApplication application;

    public AbstractController(TCPClient client, ClientApplication application) {
        this.client = client;
        this.application = application;
    }
}
