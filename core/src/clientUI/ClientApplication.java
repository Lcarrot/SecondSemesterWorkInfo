package clientUI;

public interface ClientApplication extends ClientApplicationFX, ClientApplicationJDX {
    void joinGame(Boolean status, RoomInfo roomInfo);
}
