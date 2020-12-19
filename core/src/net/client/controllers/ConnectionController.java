package net.client.controllers;

import clientUI.RoomInfo;

import java.io.Serializable;

public interface ConnectionController {

    void addPlayerFrag(Integer killsCount);

    void updateListOfRooms();

    void createNewRoom(RoomInfo roomInfo);

    <T extends Serializable> void  sendChatMessage(T obj);

    void setNewRoom(RoomInfo roomInfo);
}
