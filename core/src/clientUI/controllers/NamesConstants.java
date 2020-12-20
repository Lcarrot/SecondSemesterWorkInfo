package clientUI.controllers;

public enum NamesConstants {
    MUSIC_MENU("/music/menu.mp3"),
    MUSIC_GAME("C:/Users/olga1/Desktop/projects/SecondSemesterWork/core/build/resources/main/music/game.mp3"),
    TITLE_NAMES("Tank Game"),
    TITLE_IMG("/img/title.jpg"),
    TYPE_TIME("HH:mm:ss"),
    TEXTURE_GAME("game.pack"),
    BITMAP_GAME("font24.fnt");

    private String name;
    NamesConstants(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
