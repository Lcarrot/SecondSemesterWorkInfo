package clientUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import net.client.TCPClient;
import net.starter.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class MainController extends Application {

    private RoomsController roomsController;
    private AddRoomController addRoomController;
    private static Stage stage;
    MediaPlayer mediaPlayer;
    private TCPClient client;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("Anime tanks forever");
        stage.setWidth(1200);
        stage.setHeight(700);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/formFX/MainFX.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setMinHeight(650);
        stage.setMinWidth(1000);

        if ((mediaPlayer == null)) {
            Media sound = new Media(String.valueOf(getClass().getResource("/music/menu.mp3")));
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
        }

        stage.show();

    }

    public void setClient(TCPClient client) {
        this.client = client;
    }

    @FXML
    private void clickCommunicate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/ListRooms.fxml"));
        Parent root = loader.load();
        roomsController = loader.getController();
        roomsController.setClient(client);
        roomsController.init(this, stage);
        stage.setScene(new Scene(root));

    }

    @FXML
    private void clickPlay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/AddRoom.fxml"));
        Parent root = loader.load();
        addRoomController = loader.getController();
        addRoomController.setClient(client);
        addRoomController.init(this, stage);
        stage.setScene(new Scene(root));
    }


    @FXML
    private void clickClose(ActionEvent event) throws Exception {
        stage.close();
    }


    public static void main(String[] args) {
        TCPClient client;
        try {
            client = new TCPClient(new Socket(InetAddress.getLocalHost(), Protocol.PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainController controller = new MainController();
        controller.setClient(client);
        Application.launch(controller.getClass(), args);
    }
}

