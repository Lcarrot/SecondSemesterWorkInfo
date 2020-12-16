package net.client;

import net.network.ConnectionListener;
import net.network.connection.TCPConnection;
import net.network.message.TCPMessage;

public abstract class AbstractTCPClient implements ConnectionListener<TCPConnection, TCPMessage> {

}
