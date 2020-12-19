package clientUI.controllers;

import clientUI.ApplicationUI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.client.controllers.message.ChatMessageController;
import net.network.message.UIMessage.ChatMessage;
import net.server.Room;
import clientUI.RoomInfo;

import java.util.*;

public class ListRoomsController {

    private ApplicationUI parent;

    private static Stage stage;
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

    ChatMessageController client;

    private List<Room> listObjectRoom;


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
        receivedUpdateListRooms(null);
    }

    public void receivedUpdateListRooms(List<RoomInfo> rooms){
        if (rooms != null) {
            listRoom.setItems(FXCollections.observableList(rooms));
        }
    }

    public void receivedMessage(ChatMessage chatMessage){
        textAreaMessages.appendText("User " + chatMessage.getClientId() + ": " + chatMessage.getMessage() + "\n");
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
