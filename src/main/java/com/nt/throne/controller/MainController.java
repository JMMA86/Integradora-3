package com.nt.throne.controller;

import com.nt.throne.screens.BaseScreen;
import com.nt.throne.screens.MenuScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private MediaView videoBackground;
    private MediaPlayer songMediaPlayer;
    private boolean isRunning;
    private ArrayList<BaseScreen> screens;
    //Screens:
    /*
    0: Menu
    1: Skins
    2: Play
     */
    public static int SCREEN = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Attributes initialization
        isRunning = true;
        screens = new ArrayList<>();
        screens.add(new MenuScreen(this.canvas));
        canvas.setFocusTraversable(true);

        //Video
        File videoPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/MenuVideo.mp4");
        String fullVideoPath = videoPath.toURI().toString();
        MediaPlayer videoMediaPlayer = new MediaPlayer(new Media(fullVideoPath));
        videoBackground.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.play();

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/music.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));
        songMediaPlayer.play();

        //Updates from other screens
        new Thread( () -> {
            while (isRunning){
                Platform.runLater(this::paint);
                pause();
            }
        }).start();

        initEvents();
    }

    public void paint(){
        if (SCREEN <= screens.size()) screens.get(SCREEN).paint();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    private void pause(){
        try {
            Thread.sleep(50);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void initEvents() {
        canvas.setOnMousePressed(event -> screens.get(SCREEN).onMousePressed(event));
    }
}