package clientUI.controllers;

import clientUI.ApplicationUI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.client.controllers.message.ChatMessageController;
import net.network.message.UIMessage.ChatMessage;
import net.server.Room;
import clientUI.RoomInfo;

import java.util.*;

public class ListRoomsController {

    private ApplicationUI parent;

    @FXML
    Button buttonInputMessage;
    @FXML
    Button buttonUpdateListRooms;
    @FXML
    ListView<RoomInfo> listRoom;
    @FXML
    TextArea textAreaMessages;
    @FXML
    TextField textFieldMessage;



    @FXML
    private void back(ActionEvent event) throws Exception {
        parent.setScene(ScenesNames.START);
    }

    @FXML
    private void sendMessage(ActionEvent event) throws Exception{
        parent.animationButton(buttonInputMessage);
        String message = textFieldMessage.getText();
        if (!(message.equals(""))) {
            parent.sendMessage(textFieldMessage.getText());
        }
        textFieldMessage.setText("");
    }


    @FXML
    private void updateListRooms(ActionEvent event){
        parent.updateListRooms();
    }

    public void receivedUpdateListRooms(List<RoomInfo> rooms){
        listRoom.setItems(FXCollections.observableList(rooms));
        listRoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RoomInfo room = listRoom.getSelectionModel().getSelectedItem();
                parent.requestGame(room);
            }
        });

    }

    public void receivedMessage(ChatMessage chatMessage){
        textAreaMessages.appendText("User " + chatMessage.getClientId() + ": " + chatMessage.getMessage() + "\n");
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
