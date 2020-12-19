package clientUI;

import clientUI.controllers.*;
import game.tanki.TankGame;
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
import net.client.GameTCPClient;
import net.network.message.UIMessage.ChatStringMessage;
import net.network.message.UIMessage.ConnectToRoomMessage;
import net.network.message.UIMessage.DoFragMessage;
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
    private GameTCPClient tcpClient;
    private TankGame game;

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

        tcpClient = new GameTCPClient(new Socket(InetAddress.getLocalHost(), Protocol.PORT), this);

        setScene(ScenesNames.START);
        stage.show();
    }


    @Override
    public void receivedMessage(ChatStringMessage chatStringMessage) {
        listRoomsController.receivedMessage(chatStringMessage);
    }

    @Override
    public void receivedUpdateListRooms(List<RoomInfo> roomInfos) {
        listRoomsController.receivedUpdateListRooms(roomInfos);
    }

    @Override
    public void sendMessage(String message) {
        tcpClient.getChatController().send(message);
    }

    @Override
    public void addRoom(RoomInfo room) {

    }

    @Override
    public void updateListRooms() {
        tcpClient.getListRoomController().send(true);
    }

    public void closeApplication(){
        stage.close();
        tcpClient.close();
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

    @Override
    public void updateFrags(DoFragMessage message) {
        // TODO: 12/19/2020 вызывается обновление фрагов game. что то там
    }

    @Override
    public void addKill(Integer integer) {
        tcpClient.getRoomController().send(integer);
    }

    @Override
    public void addPlayer(ConnectToRoomMessage message) {
        if (message.isStatus()) {
            // TODO: 12/19/2020   добавить игрока в табличку и начать отслеживать фраги
        }
        else {
            // TODO: 12/19/2020 вывести окошко с текстом, что такое невозможно
        }
    }
}
