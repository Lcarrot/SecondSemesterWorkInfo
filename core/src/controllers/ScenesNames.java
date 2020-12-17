package controllers;

public enum ScenesNames {
    START("/formFX/Start.fxml"),
    ADD_ROOM("/formFX/AddRoom.fxml"),
    LIST_ROOM("/formFX/ListRooms.fxml");

    private String title;
    ScenesNames(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
