package clientUI;

import clientUI.controllers.ScenesNames;
import net.network.message.UIMessage.ChatStringMessage;
import net.network.message.UIMessage.ConnectToRoomMessage;
import net.network.message.UIMessage.DoFragMessage;

import java.util.List;

public interface ClientApplication {
    void setScene(ScenesNames scene);
    void updateListRooms();
    void receivedMessage(ChatStringMessage chatStringMessage);
    void sendMessage(String message);
    void addRoom(RoomInfo room);
    void receivedUpdateListRooms(List<RoomInfo> roomInfos);

    //2 метода для взаимодествия с игрой
    void updateFrags(DoFragMessage message); // с сервера приходит сообщения о том, у кого обновились фраги и сколько их
    void addKill(Integer integer); // игрок убил бота, необходимо добавить на сервер
    void addPlayer(ConnectToRoomMessage message);
}
