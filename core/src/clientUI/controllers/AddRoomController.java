package clientUI.controllers;

import clientUI.ApplicationUI;
import clientUI.RoomInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class AddRoomController {
    private ApplicationUI parent;

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
        room = new RoomInfo(textFieldName.getText(), parent.getTcpClient().getId(),
                Integer.parseInt (textFieldQuantity.getText()));
        parent.addRoom(room);
    }

    public void setRoom(RoomInfo room) {
        this.room = room;
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
