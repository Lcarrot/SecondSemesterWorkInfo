package clientUI.listeners;

import net.server.Room;

import java.io.Serializable;

public interface RoomsListener {
    void clickRoom();
    void inputMessage();
    void updateRooms();
}
