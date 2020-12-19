package clientUI;

import clientUI.controllers.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.tanki.TankGame;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.client.GameTCPClient;
import net.network.message.UIMessage.ChatMessage;
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
    private LwjglApplicationConfiguration config;

    // TODO: 12/19/2020 метод для запуска игры, tcpClient будет его вызывать, если удалось настроить подключение.

    @Override
    public void start(Stage primaryStage) throws Exception {
        config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;

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

    public void startGame(){
        new LwjglApplication(new TankGame(this), config);
        stage.hide();
    }

    @Override
    public void receivedMessage(ChatMessage chatMessage) {
        listRoomsController.receivedMessage(chatMessage);
    }

    @Override
    public void receivedUpdateListRooms(List<RoomInfo> roomInfos) {
        listRoomsController.receivedUpdateListRooms(roomInfos);
    }

    @Override
    public void sendMessage(String message) {
        tcpClient.sendChatMessage(message);
    }

    @Override
    public void addRoom(RoomInfo room) {
        tcpClient.createNewRoom(room);
    }

    @Override
    public void updateListRooms() {
        tcpClient.updateListOfRooms();
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


    @Override
    public void updateFrags(Integer id, Integer killsCount) {
        // TODO: 12/19/2020 обновлять убийства у игрока с таким id
    }

    @Override
    public void addKill(Integer integer) {
        tcpClient.addPlayerFrag(integer);
    }

    @Override
    public void addPlayer(boolean bool, RoomInfo roomInfo) {
        if (bool) {
            // TODO: 12/19/2020   добавить игрока в табличку и начать отслеживать фраги
        }
        else {
            // TODO: 12/19/2020 вывести окошко с текстом, что такое невозможно
        }
    }

    @Override
    public void playerIsDisconnected(Integer id) {
        setScene(ScenesNames.START);
        stage.show();
        // TODO: 12/19/2020 удалить игрока из таблицы. Лёня добавит свою часть.
    }

    @Override
    public void closeGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setScene(ScenesNames.START);
                stage.show();
            }
        });
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
