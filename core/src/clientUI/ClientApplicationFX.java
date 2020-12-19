package clientUI;

import clientUI.controllers.ScenesNames;
import net.network.message.UIMessage.ChatStringMessage;

import java.util.List;

public interface ClientApplicationFX {

    void setScene(ScenesNames scene);
    void updateListRooms();
    void receivedMessage(ChatStringMessage chatStringMessage);
    void sendMessage(String message);
    void addRoom(RoomInfo room);
    void receivedUpdateListRooms(List<RoomInfo> roomInfos);
}
