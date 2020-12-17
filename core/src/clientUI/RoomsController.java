package clientUI;

import clientUI.listeners.RoomsListener;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.client.ChatClient;
import net.client.TCPClient;
import net.server.Room;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class RoomsController implements RoomsListener {

    private Application parent;

    private static Stage stage;
    @FXML
    Button buttonInputMessage;
    @FXML
    Button buttonUpdate;
    @FXML
    ListView<String> listRoom;
    @FXML
    TextArea chatField;
    @FXML
    TextField inputText;

    ChatClient client;

    private List<Room> listObjectRoom;

    public void init(Application parent, Stage stage, TCPClient client) throws IOException {
        this.parent = parent;
        this.stage = stage;
        this.client = client.getChatClient();
        listRoom.setItems(FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python"));
    }

    @FXML
    private void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/MainFX.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @Override
    public void clickRoom() {

    }

    @Override
    public void inputMessage() {
        client.sendMessage(inputText.getText());
        inputText.setText("");
    }

    @Override
    public void updateRooms() {

    }
}
