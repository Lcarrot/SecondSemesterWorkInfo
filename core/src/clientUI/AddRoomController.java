package clientUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.client.TCPClient;

import java.io.IOException;

public class AddRoomController {
    private Application parent;
    private static Stage stage;
    private TCPClient client;

    @FXML
    private TextField name;
    @FXML
    private TextField countUsers;


    public void init(Application parent, Stage stage) throws IOException {
        this.parent = parent;
        this.stage = stage;
        countUsers.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.matches("[0-9]+$")) {
                        int number = Integer.parseInt(newValue);
                        if (number < 20) {
                            return;
                        }
                        countUsers.setText(oldValue);
                    }
                });
    }


    @FXML
    private void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/MainFX.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void addRoom(ActionEvent event) {

    }

    public void setClient(TCPClient client) {
        this.client = client;
    }
}
