package net.network.connection;

import net.network.ConnectionListener;
import net.network.message.TCPMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPConnection extends AbstractConnection<TCPMessage, TCPConnection> implements Runnable, Sender<TCPMessage> {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int id;

    public TCPConnection(Socket socket, ConnectionListener<TCPConnection, TCPMessage> listener) {
        this.listener = listener;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        isAlive = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void send(TCPMessage message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                if (in.available() != 0) {
                    listener.receive((TCPMessage) in.readObject());
                }
                else {
                    Thread.sleep(200);
                }
            } catch(IOException | InterruptedException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
