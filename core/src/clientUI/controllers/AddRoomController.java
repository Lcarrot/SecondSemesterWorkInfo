package clientUI.controllers;

import clientUI.ApplicationUI;
import clientUI.RoomInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddRoomController {
    private ApplicationUI parent;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldQuantity;
    @FXML
    private Label timer;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NamesConstants.TYPE_TIME.getName());
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

    public void setParent(ApplicationUI parent) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> timer.setText(LocalDateTime.now().format(formatter)))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        this.parent = parent;
    }
}
