package clientUI;

import clientUI.controllers.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.tanki.TankGame;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.client.GameTCPClient;
import net.network.message.UIMessage.ChatMessage;
import net.starter.Protocol;

import java.io.IOException;
import java.io.InputStream;
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



    @Override
    public void start(Stage primaryStage) throws Exception {
        config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        stage = primaryStage;
        stage.setTitle(NamesConstants.TITLE_NAMES.getName());
        InputStream iconStream = getClass().getResourceAsStream(NamesConstants.TITLE_IMG.getName());
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setMinHeight(650);
        stage.setMinWidth(1000);

        if ((mediaPlayer == null)) {
            Media sound = new Media(String.valueOf(getClass().getResource(NamesConstants.MUSIC_MENU.getName())));
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
        }

        tcpClient = new GameTCPClient(new Socket(InetAddress.getLocalHost(), Protocol.PORT), this);
        setScene(ScenesNames.START);
        stage.show();
    }

    @Override
    public void joinGame(Boolean booleanJoin, RoomInfo roomInfo){
        if (booleanJoin) {
            game = new TankGame(this, roomInfo);
            new LwjglApplication(game, config);
        }

    }


    @Override
    public void receivedMessage(ChatMessage chatMessage) {
        listRoomsController.receivedMessage(chatMessage);
    }

    @Override
    public void receivedUpdateListRooms(List<RoomInfo> roomInfos) {
        if (roomInfos != null) {
            listRoomsController.receivedUpdateListRooms(roomInfos);
        }
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
        tcpClient.close();
        stage.close();
    }


    @Override
    public void updateFrags(Integer id, Integer killsCount) {
        game.setScore(id, killsCount);
    }

    @Override
    public void addKill(Integer integer) {
        tcpClient.addPlayerFrag(integer + 1);
    }

    @Override
    public void addPlayer(boolean bool, RoomInfo roomInfo) {
        if (bool) {
            game.setRoomInfo(roomInfo);
        }
    }

    @Override
    public void playerIsDisconnected(RoomInfo roomInfo) {
        game.setRoomInfo(roomInfo);
        setScene(ScenesNames.START);
        stage.show();
    }


    @Override
    public void closeGame(RoomInfo roomInfo) {
        tcpClient.disconnectFromRoom(roomInfo);

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

    public void hideApplication(){
        stage.hide();
    }

    public int getID(){
        return tcpClient.getId();
    }

    public void requestGame(RoomInfo roomInfo){
        tcpClient.connectToRoom(roomInfo);
    }

    public GameTCPClient getTcpClient() {
        return tcpClient;
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
