package clientUI.controllers;

import clientUI.ApplicationUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartController{
    private ApplicationUI parent;

    @FXML
    private void clickCommunicate(ActionEvent event) throws IOException {
        parent.updateListRooms();
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
