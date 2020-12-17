package controllers;

import clientUI.ApplicationUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.server.Room;

public class AddRoomController {
    private ApplicationUI parent;
    private static Stage stage;;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldQuantity;
    private Room room;



    @FXML
    private void back(ActionEvent event) throws Exception {
        parent.setScene(ScenesNames.START);
    }

    @FXML
    private void addRoom(ActionEvent event) {
        Room room = new Room(1, textFieldName.getText(), 2, 1);
        parent.addRoom(room);
    }



    public void setRoom(Room room) {
        this.room = room;
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
