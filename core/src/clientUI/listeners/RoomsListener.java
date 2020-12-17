package clientUI.listeners;

import net.network.message.ChatMessage;
import net.server.Room;

import java.io.Serializable;

public interface RoomsListener {
    void clickRoom();
    <T extends Serializable> void inputMessage(T object);
    Room[] updateRooms();
}
