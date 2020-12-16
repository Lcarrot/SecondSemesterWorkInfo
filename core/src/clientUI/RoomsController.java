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
import net.client.TCPClient;
import net.network.message.ChatMessage;
import net.server.Room;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class RoomsController implements RoomsListener {

    private Application parent;
    private static Stage stage;
    @FXML
    Button buttonInputMessage;
    @FXML
    Button buttonUpdate;
    @FXML
    ListView listRoom;
    @FXML
    TextArea chatField;
    @FXML
    TextField inputText;

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


    // TODO: 12/17/2020 обновление списка комнат. обращаться к списку комнат listRoom
    @Override
    public void updateListRoom(Set<Room> rooms) {

    }

    // TODO: 12/17/2020 нажатие на одну из комнат.
    @Override
    public void clickRoom() {

    }

    // TODO: 12/17/2020 отправление сообщение. подтягивать текст с inputText
    @Override
    public void inputMessage() {
        String message = inputText.getText();
    }


    // TODO: 12/17/2020 обновление чата. Обращаться к элементу chatField
    @Override
    public void updateChat() {
        
    }

}
