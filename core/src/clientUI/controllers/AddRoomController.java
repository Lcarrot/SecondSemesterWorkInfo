package clientUI.controllers;

import clientUI.ApplicationUI;
import clientUI.RoomInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddRoomController {
    private ApplicationUI parent;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldQuantity;
    private RoomInfo room;
    private static final Pattern PATTERN_NUMBERS = Pattern.compile("^([2-9]?|[1-9]+[0-9]+)$");
    private Matcher matcher;



    @FXML
    private void back(ActionEvent event) throws Exception {
        parent.setScene(ScenesNames.START);
    }

    @FXML
    private void addRoom(ActionEvent event) {
        String count = textFieldQuantity.getText();
        matcher = PATTERN_NUMBERS.matcher(count);
        if (matcher.find()) {
            parent.hideApplication();
            room = new RoomInfo(textFieldName.getText(), parent.getTcpClient().getId(),
                    Integer.parseInt(count));
            parent.addRoom(room);
        }

    }

    public void setRoom(RoomInfo room) {
        this.room = room;
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
