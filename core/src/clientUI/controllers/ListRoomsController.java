package clientUI.controllers;

import clientUI.ApplicationUI;
import clientUI.RoomInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.network.message.UIMessage.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ListRoomsController {

    private ApplicationUI parent;

    @FXML
    private Button buttonInputMessage;
    @FXML
    private ListView<RoomInfo> listRoom;
    @FXML
    private TextArea textAreaMessages;
    @FXML
    private TextField textFieldMessage;

    private static List<String> history = new ArrayList<>();



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
        listRoom.setOnMouseClicked(event -> {
            RoomInfo room = listRoom.getSelectionModel().getSelectedItem();
            if (room != null) {
                parent.requestGame(room);
            }
        });

    }

    public void receivedMessage(ChatMessage chatMessage){
        textAreaMessages.appendText("User " + chatMessage.getClientId() + ": " + chatMessage.getMessage() + "\n");
        history.add("User " + chatMessage.getClientId() + ": " + chatMessage.getMessage());
    }

    public void setParent(ApplicationUI parent) {
        for (String message: history) {
            textAreaMessages.appendText(message + "\n");
        }
        this.parent = parent;
    }
}
