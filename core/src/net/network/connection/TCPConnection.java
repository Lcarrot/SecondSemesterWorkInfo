package net.network.connection;

import net.network.ConnectionListener;
import net.network.message.TCPMessage;

import java.io.*;
import java.net.Socket;

public class TCPConnection extends AbstractConnection<TCPMessage, TCPConnection> implements Runnable, Sender<TCPMessage> {

    private InputStream in;
    private OutputStream out;
    private int id;

    public TCPConnection(Socket socket, ConnectionListener<TCPConnection, TCPMessage> listener) {
        this.listener = listener;
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
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
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            System.out.println("I'm working right now");
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                System.out.println("i'm working" + id);
                if (in.available() != 0) {
                    System.out.println("I'm here?");
                    ObjectInputStream inputStream = new ObjectInputStream(in);
                    listener.receive((TCPMessage) inputStream.readObject());
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
