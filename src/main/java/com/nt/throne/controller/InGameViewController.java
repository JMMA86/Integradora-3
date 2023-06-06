package com.nt.throne.controller;

import com.nt.throne.screens.BaseScreen;
import com.nt.throne.screens.Pantheon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InGameViewController implements Initializable {
    @FXML
    private Text levelTxt;
    @FXML
    private Canvas canvas;
    @FXML
    private VBox statsBox;
    @FXML
    private Text nameTxt;
    @FXML
    private Text lifeTxt;
    @FXML
    private Text ammoTxt;
    private MediaPlayer songMediaPlayer;
    private boolean isRunning;
    private ArrayList<BaseScreen> screens;
    //Screens:
    /*
    0: pantheon
     */
    public static int SCREEN = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Attributes initialization
        this.screens = new ArrayList<>();
        screens.add(new Pantheon(canvas));
        isRunning = true;

        //Fonts
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Fonts/upheavtt.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font customFont = Font.loadFont(inputStream, 20);
        levelTxt.setFont(customFont);
        nameTxt.setFont(customFont);
        lifeTxt.setFont(customFont);
        ammoTxt.setFont(customFont);

        //Painting
        new Thread( () -> {
            while (isRunning){
                Platform.runLater(this::paint);
                pause(50);
            }
        }).start();

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/gameplay.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));
        songMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        songMediaPlayer.setVolume(0);
        songMediaPlayer.play();
        for (double i = 0; i <= 1; i += 0.1) {
            songMediaPlayer.setVolume(i);
            pause(100);
        }

        initEvents();
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
        canvas.setOnKeyPressed(event -> screens.get(SCREEN).onKeyPressed(event));
        canvas.setOnKeyReleased(event -> screens.get(SCREEN).onKeyReleased(event));
    }
}
