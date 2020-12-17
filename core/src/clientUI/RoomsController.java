package clientUI;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import net.client.TCPClient;
import net.network.message.UpdateListRoomMessage;
import net.server.Room;

import java.io.IOException;
import java.util.*;

public class RoomsController {

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
    private UpdateListRoomMessage updateListRoomMessage;

    private List<Room> listObjectRoom;

    private TCPClient client;

    public void init(Application parent, Stage stage) throws IOException {
        this.parent = parent;
        this.stage = stage;
        listRoom.setItems(FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python"));
    }

    @FXML
    private void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/MainFX.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public void setClient(TCPClient client) {
        this.client = client;
    }

    public void clickRoom() {

    }

    public void inputMessage() {
        client.inputMessage(inputText.getText());
        inputText.setText("");
    }

    public void updateRooms() {
        listObjectRoom.addAll(Arrays.asList(client.updateRooms()));
        String[] names = new String[listObjectRoom.size()];
        int i = 0;
        for (Room room : listObjectRoom) {
            names[i++] = room.getName();
        }
        listRoom.setItems(FXCollections.observableList(Arrays.asList(names)));
    }
}
