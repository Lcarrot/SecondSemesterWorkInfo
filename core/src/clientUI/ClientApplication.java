package clientUI;

import controllers.ScenesNames;
import net.network.message.UIMessage.ChatStringMessage;
import net.server.room.Room;

import java.util.List;

public interface ClientApplication {
    void setScene(ScenesNames scene);
    void updateListRooms(List<RoomInfo> rooms);
    void receivedMessage(ChatStringMessage chatStringMessage);
    void sendMessage(String message);
    void addRoom(RoomInfo room);
}
