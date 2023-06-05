package com.nt.throne.controller;

import com.nt.throne.screens.BaseScreen;
import com.nt.throne.screens.MenuScreen;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Text loadingText;
    @FXML
    private Text playBtn;
    @FXML
    private Text skinBtn;
    @FXML
    private Text exitBtn;
    @FXML
    private Canvas canvas;
    @FXML
    private MediaView videoBackground;
    private MediaPlayer videoMediaPlayer;
    private boolean videoReady;
    private boolean audioReady;
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
        videoReady = false;
        audioReady = false;

        //Fonts
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Fonts/upheavtt.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font customFont = Font.loadFont(inputStream, 40);
        playBtn.setFont(customFont);
        skinBtn.setFont(customFont);
        exitBtn.setFont(customFont);
        loadingText.setFont(customFont);


        //Video
        File videoPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/MenuVideo.mp4");
        String fullVideoPath = videoPath.toURI().toString();
        videoMediaPlayer = new MediaPlayer(new Media(fullVideoPath));
        videoBackground.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/music.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));

        //Loading screen
        screens.get(0).getGraphicsContext().setGlobalAlpha(1);
        videoMediaPlayer.setOnReady(() -> {
            videoReady = true;
        });
        songMediaPlayer.setOnReady(() -> {
            audioReady = true;
        });
        new Thread( () -> {
            while (!videoReady || !audioReady){
                pause(50);
                if (videoReady && audioReady) {
                    pause(1000);
                    playResources();
                    loadingText.setVisible(false);
                    playBtn.setVisible(true);
                    skinBtn.setVisible(true);
                    exitBtn.setVisible(true);
                    break;
                }
            }
        }).start();

        //Updates from other screens
        new Thread( () -> {
            while (isRunning){
                Platform.runLater(this::paint);
                pause(50);
            }
        }).start();

        initEvents();

        //Opacity
        playBtn.setOnMouseEntered(event -> {
            playBtn.setOpacity(0.5);
        });

        playBtn.setOnMouseExited(event -> {
            playBtn.setOpacity(1);
        });

        skinBtn.setOnMouseEntered(event -> {
            skinBtn.setOpacity(0.5);
        });

        skinBtn.setOnMouseExited(event ->
                skinBtn.setOpacity(1));

        exitBtn.setOnMouseEntered(event -> {
            exitBtn.setOpacity(0.5);
        });

        exitBtn.setOnMouseExited(event -> {
            exitBtn.setOpacity(1);
        });
    }

    public void playResources() {
        screens.get(0).getGraphicsContext().setGlobalAlpha(0.01);
        videoMediaPlayer.play();
        songMediaPlayer.play();
    }

    public void paint(){
        if (SCREEN <= screens.size()) screens.get(SCREEN).paint();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    private void pause(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void initEvents() {
        canvas.setOnMousePressed(event -> screens.get(SCREEN).onMousePressed(event));
    }
}