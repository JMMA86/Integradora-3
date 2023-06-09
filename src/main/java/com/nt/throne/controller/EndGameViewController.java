package com.nt.throne.controller;

import com.nt.throne.Launcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGameViewController implements Initializable {
    @FXML
    private MediaView videoBackground;
    @FXML
    private Text titleScreen;
    @FXML
    private Text backBtn;
    @FXML
    private Canvas loadingScreen;
    @FXML
    private Text loadingText;
    private MediaPlayer videoMediaPlayer;
    private MediaPlayer songMediaPlayer;
    private boolean videoReady;
    private boolean audioReady;
    private static boolean isWinner = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoReady = false;
        audioReady = false;
        loadingScreen.getGraphicsContext2D().setFill(Color.BLACK);
        loadingScreen.getGraphicsContext2D().fillRect(0, 0, 1280, 720);

        //Fonts
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Fonts/upheavtt.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font customFont = Font.loadFont(inputStream, 40);
        titleScreen.setFont(customFont);
        backBtn.setFont(customFont);
        loadingText.setFont(customFont);
        backBtn.setOnMouseEntered(event -> backBtn.setOpacity(0.5));
        backBtn.setOnMouseExited(event -> backBtn.setOpacity(1));

        //Video
        File videoPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/outroBackground.mp4");
        String fullVideoPath = videoPath.toURI().toString();
        videoMediaPlayer = new MediaPlayer(new Media(fullVideoPath));
        videoMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        videoBackground.setMediaPlayer(videoMediaPlayer);

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/outroSong.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));
        songMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        songMediaPlayer.setVolume(0.5);

        //Loading screen
        videoMediaPlayer.setOnReady(() -> videoReady = true);
        songMediaPlayer.setOnReady(() -> audioReady = true);

        new Thread(() -> {
            while (!videoReady || !audioReady) {
                pause(50);
                if (videoReady && audioReady) {
                    if (isWinner) {
                        titleScreen.setText("YOU WIN!");
                    } else {
                        titleScreen.setText("YOU LOSE!");
                    }
                    pause(1000);
                    playResources();
                    pause(1000);
                    loadingText.setVisible(false);
                    loadingScreen.setVisible(false);
                    break;
                }
            }
        }).start();
    }

    public void playResources() {
        videoMediaPlayer.play();
        songMediaPlayer.setVolume(0);
        songMediaPlayer.play();
        for (double i = 0; i <= 0.5; i += 0.1) {
            songMediaPlayer.setVolume(i);
            pause(100);
        }
    }

    public void backToMenu() {
        loadingScreen.setVisible(true);
        loadingText.setVisible(true);
        videoMediaPlayer.stop();
        for (double i = 0.5; i >=0; i -= 0.1) {
            songMediaPlayer.setVolume(i);
            pause(100);
        }
        songMediaPlayer.stop();
        Launcher.renderView("main-menu-view.fxml", 1280, 720);
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setIsWinner(boolean isWinner) {
        EndGameViewController.isWinner = isWinner;
    }
}
