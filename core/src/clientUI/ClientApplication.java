package clientUI;

import controllers.ScenesNames;
import net.network.message.UIMessage.ChatMessage;
import net.server.Room;

import java.util.List;

public interface ClientApplication {
    void setScene(ScenesNames scene);
    void updateListRooms(List<Room> rooms);
    void receivedMessage(ChatMessage chatMessage);
    void sendMessage(ChatMessage message);
    void addRoom(Room room);
}
