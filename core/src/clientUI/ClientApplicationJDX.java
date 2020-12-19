package clientUI;

import net.network.message.UIMessage.DisconnectFromRoomMessage;

public interface ClientApplicationJDX {

    //4 метода для взаимодествия с игрой
    void updateFrags(Integer id, Integer killsCount); // с сервера приходит сообщения о том, у кого обновились фраги и сколько их (сервер -> игра)
    void addKill(Integer integer); // игрок убил бота, необходимо добавить на сервер (игра -> сервер)
    void addPlayer(boolean status, RoomInfo roomInfo); //игрок присоединился, необходимо его отобразить (сервер -> игра)
    void playerIsDisconnected(RoomInfo roomInfo); // игрок отсоединился, необходимо его убрать из списка игроков (сервер -> игра)
    void closeGame(RoomInfo roomInfo);
    int getID();
}
