package controllers;

import clientUI.ApplicationUI;
import clientUI.RoomInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.server.room.Room;

public class AddRoomController {
    private ApplicationUI parent;
    private static Stage stage;;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldQuantity;
    private RoomInfo room;



    @FXML
    private void back(ActionEvent event) throws Exception {
        parent.setScene(ScenesNames.START);
    }

    @FXML
    private void addRoom(ActionEvent event) {
//        RoomInfo info = new RoomInfo(1, "hi", 1, 5);
//        Room room = new Room(info);
        parent.addRoom(room);
    }



    public void setRoom(RoomInfo room) {
        this.room = room;
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
