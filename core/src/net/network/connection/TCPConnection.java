package net.network.connection;

import net.network.ConnectionListener;
import net.network.message.TCPMessage;

import java.io.*;
import java.net.Socket;

public class TCPConnection extends AbstractConnection<TCPMessage, TCPConnection> implements Runnable,Sender<TCPMessage> {

    private InputStream in;
    private OutputStream out;
    private final Socket socket;
    private int id;

    public TCPConnection(Socket socket, ConnectionListener<TCPConnection, TCPMessage> listener) {
        this.listener = listener;
        this.socket = socket;
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            id = receiveId();
        } catch (IOException e) {
            listener.connectException(this, e);
        }
        isAlive = !socket.isClosed();
    }

    public TCPConnection(Socket socket, ConnectionListener<TCPConnection, TCPMessage> listener, int id) {
        this.socket = socket;
        this.listener = listener;
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            this.id = id;
            sendId(id);
        } catch (IOException e) {
            listener.connectException(this, e);
        }
        isAlive = !socket.isClosed();
    }

    private void sendId(int id) throws IOException {
        DataOutputStream dataOutputStream= new DataOutputStream(out);
        dataOutputStream.writeInt(id);
    }

    public int getId() {
        return id;
    }

    private int receiveId() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        return dataInputStream.readInt();
    }

    @Override
    public void send(TCPMessage message) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAlive() {
        return !socket.isClosed();
    }

    @Override
    public void close() {
        try {
            socket.close();
            isAlive = false;
        } catch (IOException e) {
            listener.connectException(this, e);
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                if (in.available() != 0) {
                    ObjectInputStream inputStream = new ObjectInputStream(in);
                    listener.receive((TCPMessage) inputStream.readObject());
                }
                else {
                    Thread.sleep(200);
                }
            } catch(IOException | InterruptedException | ClassNotFoundException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public String toString() {
        return "TCPConnection{" +
                "socket=" + socket.getInetAddress() +
                ", id=" + id +
                '}';
    }
}
