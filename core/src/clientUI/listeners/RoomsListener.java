package clientUI.listeners;

import net.network.message.ChatMessage;
import net.network.message.TCPMessage;
import net.server.Room;

import java.util.Set;

public interface RoomsListener {
    void updateListRoom(Set<Room> rooms);
    void clickRoom();
    void inputMessage();
    void updateChat();
}
