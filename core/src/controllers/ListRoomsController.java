package controllers;

import clientUI.ApplicationUI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.client.ChatClient;
import net.network.message.UIMessage.ChatMessage;
import net.server.Room;

import java.util.*;

public class ListRoomsController {

    private ApplicationUI parent;

    private static Stage stage;
    @FXML
    Button buttonInputMessage;
    @FXML
    Button buttonUpdateListRooms;
    @FXML
    ListView<Room> listRoom;
    @FXML
    TextArea textAreaMessages;
    @FXML
    TextField textFieldMessage;

    ChatClient client;

    private List<Room> listObjectRoom;


    @FXML
    private void back(ActionEvent event) throws Exception {
        parent.setScene(ScenesNames.START);
    }

    @FXML
    private void sendMessage(ActionEvent event) throws Exception{
        parent.animationButton(buttonInputMessage);
        ChatMessage chatMessage = new ChatMessage(1);
        parent.sendMessage(chatMessage);
    }


    // вызов требования обновления комнат
    public void updateListRooms(List<Room> rooms){
        if (rooms != null) {
            listRoom.setItems(FXCollections.observableList(rooms));
        }
    }

    public void receivedMessage(ChatMessage chatMessage){
        textAreaMessages.appendText(chatMessage.toString());
    }

    public void setParent(ApplicationUI parent) {
        this.parent = parent;
    }
}
