package com.nt.throne.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private MediaView videoBackground;

    private MediaPlayer songMediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Video
        File videoPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/MenuVideo.mp4");
        String fullVideoPath = videoPath.toURI().toString();
        Media videoMedia = new Media(fullVideoPath);
        MediaPlayer videoMediaPlayer = new MediaPlayer(videoMedia);
        videoBackground.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.play();

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/music.mp3");
        String fullSongPath = songPath.toURI().toString();
        Media songMedia = new Media(fullSongPath);
        songMediaPlayer = new MediaPlayer(songMedia);
        songMediaPlayer.play();
    }
}