package com.nt.throne.controller;

import com.nt.throne.model.Hero;
import com.nt.throne.screens.Pantheon;
import com.nt.throne.screens.RedDesert;
import com.nt.throne.screens.Scenario;
import com.nt.throne.screens.Winter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
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
    //Screens:
    /*
    0: pantheon
     */
    public static int SCREEN = 0;
    private static ArrayList<Scenario> screens;
    @FXML
    private Text counter;
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
    private static boolean isRunning;

    public static int getMapsSize() {
        return screens.size();
    }

    public static ArrayList<Scenario> getScreens() {
        return screens;
    }

    public static int getSCREEN() {
        return SCREEN;
    }

    public static void setSCREEN(int SCREEN) {
        InGameViewController.SCREEN = SCREEN;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Attributes initialization
        canvas.setFocusTraversable(true);
        screens = new ArrayList<>();
        screens.add(new Pantheon(canvas, new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1.png")));
        screens.add(new Winter(canvas, new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2.png")));
        screens.add(new RedDesert(canvas, new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3.png")));
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
        counter.setFont(customFont);

        //Painting
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(this::paint);
                lifeTxt.setText("    Life: " + Hero.getInstance().getLife() + " / 100");
                if (Hero.getInstance().getActualGun() != null) {
                    if (Hero.getInstance().getActualGun().getAmmo() <= 0) {
                        ammoTxt.setText("    Ammo: -RECHARGING-");
                    } else {
                        ammoTxt.setText("    Ammo: " + Hero.getInstance().getActualGun().getAmmo() + " / " + Hero.getInstance().getActualGun().getCHARGER_SIZE());
                    }
                }
                levelTxt.setText("Level " + (SCREEN + 1));
                pause(50);
            }
        }).start();

        //Counter
        new Thread(() -> {
            for (int i = 3; i >= 0; i--) {
                pause(1000);
                if (i == 0) {
                    counter.setText("START");
                    initEvents();
                } else {
                    counter.setText(String.valueOf(i));
                }
            }
            pause(1000);
            counter.setVisible(false);
        }).start();

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/gameplay.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));
        songMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        songMediaPlayer.setVolume(0);
        songMediaPlayer.play();
        for (double i = 0; i <= 0.2; i += 0.1) {
            songMediaPlayer.setVolume(i);
            pause(100);
        }
    }

    public void paint() {
        if (SCREEN <= screens.size()) screens.get(SCREEN).paint();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initEvents() {
        canvas.setOnMouseClicked(event -> screens.get(SCREEN).onMouseClicked(event));
        canvas.setOnKeyPressed(event -> screens.get(SCREEN).onKeyPressed(event));
        canvas.setOnKeyReleased(event -> screens.get(SCREEN).onKeyReleased(event));
        canvas.setOnMousePressed(event -> screens.get(SCREEN).onMousePressed(event));
        canvas.setOnMouseReleased(event -> screens.get(SCREEN).onMouseReleased(event));
        canvas.setOnMouseDragged(event -> screens.get(SCREEN).onMouseDragged(event));
        canvas.setOnMouseMoved(event -> screens.get(SCREEN).onMouseMoved(event));
        canvas.setFocusTraversable(true);
        screens.get(SCREEN).setMovingEnemies(true);
    }

    public static void setWinScreen() {
        isRunning = false;

    }

    public static void setLoseScreen() {
        isRunning = false;
    }
}
