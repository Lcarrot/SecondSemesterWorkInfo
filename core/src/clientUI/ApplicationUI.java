package clientUI;

import controllers.AddRoomController;
import controllers.ListRoomsController;
import controllers.ScenesNames;
import controllers.StartController;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.client.TCPClient;
import net.network.message.UIMessage.ChatMessage;
import net.server.Room;
import net.starter.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class ApplicationUI extends Application implements ClientApplication {
    private static Stage stage;
    private StartController startController;
    private ListRoomsController listRoomsController;
    private AddRoomController addRoomController;
    private static MediaPlayer mediaPlayer;
    private TCPClient tcpClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setMinHeight(650);
        stage.setMinWidth(1000);

        if ((mediaPlayer == null)) {
            Media sound = new Media(String.valueOf(getClass().getResource("/music/menu.mp3")));
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
        }

        tcpClient = new TCPClient(new Socket(InetAddress.getLocalHost(), Protocol.PORT), this);

        setScene(ScenesNames.START);
        stage.show();
    }


    @Override
    public void receivedMessage(ChatMessage chatMessage) {
        listRoomsController.receivedMessage(chatMessage);

    }

    @Override
    public void updateListRooms(List<Room> rooms) {
        listRoomsController.updateListRooms(rooms);
    }

    @Override
    public void sendMessage(ChatMessage message) {
        tcpClient.getChatClient().setMessage(message);
    }


    @Override
    public void addRoom(Room room) {
        //add room
    }

    public void closeApplication(){
        stage.close();
    }


    @Override
    public void setScene(ScenesNames scene){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(scene.getTitle()));
            Parent root = loader.load();
            switch (scene){
                case START:
                    startController = loader.getController();
                    startController.setParent(this);
                    break;
                case ADD_ROOM:
                    addRoomController = loader.getController();
                    addRoomController.setParent(this);
                    break;
                case LIST_ROOM:
                    listRoomsController = loader.getController();
                    listRoomsController.setParent(this);
                    break;
            }
            stage.setScene(new Scene(root));

        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void animationButton(Button button){
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        rotateTransition.setNode(button);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }

}
