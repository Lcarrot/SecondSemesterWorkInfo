package clientUI.controllers;

import clientUI.ApplicationUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController{
    ApplicationUI parent;

    private static Stage stage;
    MediaPlayer mediaPlayer;

    @FXML
    private void clickCommunicate(ActionEvent event) throws IOException {
        parent.setScene(ScenesNames.LIST_ROOM);
    }

    @FXML
    private void clickPlay(ActionEvent event) throws IOException {
        parent.setScene(ScenesNames.ADD_ROOM);
    }


    @FXML
    private void clickClose(ActionEvent event) throws Exception {
        parent.closeApplication();
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
