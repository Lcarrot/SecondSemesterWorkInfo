package clientUI;

public interface ClientApplication extends ClientApplicationFX, ClientApplicationJDX {
    void joinGame(RoomInfo roomInfo);
    void startGame();

}
