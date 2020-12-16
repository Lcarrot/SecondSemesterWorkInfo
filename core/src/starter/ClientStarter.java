package starter;

import net.client.TCPClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientStarter {

    public static void main(String[] args) {
        try {
            TCPClient client = new TCPClient(new Socket(InetAddress.getLocalHost(), Protocol.PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
