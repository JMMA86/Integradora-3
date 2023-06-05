package com.nt.throne.controller;

import com.nt.throne.Launcher;
import com.nt.throne.screens.BaseScreen;
import com.nt.throne.screens.MenuScreen;
import com.nt.throne.screens.SkinScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private VBox skinSelector;
    @FXML
    private VBox skinItems;
    @FXML
    private Text skinTitle;
    @FXML
    private Text returnBtn;
    @FXML
    private VBox menuItems;
    @FXML
    private Canvas loader;
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
    private int timer;
    private ArrayList<BaseScreen> screens;
    //Screens:
    /*
    0: Menu
    1: Skins
     */
    public static int SCREEN = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Attributes initialization
        timer = 50;
        screens = new ArrayList<>();
        screens.add(new MenuScreen(this.canvas));
        screens.add(new SkinScreen(this.canvas));
        canvas.setFocusTraversable(true);
        videoReady = false;
        audioReady = false;
        isRunning = true;
        screens.get(0).getGraphicsContext().setFill(Color.BLACK);
        loader.getGraphicsContext2D().fillRect(0,0,1280,720);
        loader.getGraphicsContext2D().setFill(Color.BLACK);

        //Fonts
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Fonts/upheavtt.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font customFont = Font.loadFont(inputStream, 40);
        playBtn.setFont(customFont);
        skinBtn.setFont(customFont);
        exitBtn.setFont(customFont);
        skinTitle.setFont(customFont);
        returnBtn.setFont(customFont);
        loadingText.setFont(customFont);

        //Video
        File videoPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/MenuVideo.mp4");
        String fullVideoPath = videoPath.toURI().toString();
        videoMediaPlayer = new MediaPlayer(new Media(fullVideoPath));
        videoMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //Song
        File songPath = new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/music.mp3");
        String fullSongPath = songPath.toURI().toString();
        songMediaPlayer = new MediaPlayer(new Media(fullSongPath));
        songMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //Loading screen
        videoMediaPlayer.setOnReady(() -> videoReady = true);
        songMediaPlayer.setOnReady(() -> audioReady = true);
        new Thread( () -> {
            while (!videoReady || !audioReady){
                pause(50);
                if (videoReady && audioReady) {
                    pause(1000);
                    playResources();
                    pause(1000);
                    loadingText.setVisible(false);
                    loader.setVisible(false);
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
                pause(timer);
            }
        }).start();

        //Events
        initEvents();
        skinSelector.setOnMouseClicked(event -> screens.get(1).onMouseClicked(event));
        skinSelector.setOnMouseMoved(event -> screens.get(1).onMouseMoved(event));

        //Opacities
        playBtn.setOnMouseEntered(event -> playBtn.setOpacity(0.5));
        playBtn.setOnMouseExited(event -> playBtn.setOpacity(1));

        skinBtn.setOnMouseEntered(event -> skinBtn.setOpacity(0.5));
        skinBtn.setOnMouseExited(event -> skinBtn.setOpacity(1));

        exitBtn.setOnMouseEntered(event -> exitBtn.setOpacity(0.5));
        exitBtn.setOnMouseExited(event -> exitBtn.setOpacity(1));

        returnBtn.setOnMouseEntered(event -> returnBtn.setOpacity(0.5));
        returnBtn.setOnMouseExited(event -> returnBtn.setOpacity(1));
    }

    public void playResources() {
        videoBackground.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.play();
        songMediaPlayer.play();
    }

    public void startGame() {
        videoMediaPlayer.stop();
        songMediaPlayer.stop();
        isRunning = false;
        Launcher.renderView("in-game-view.fxml", 1280, 720);
    }

    public void changeSkin() {
        SCREEN = 1;
        initEvents();
        timer = 100;
        menuItems.setVisible(false);
        menuItems.setManaged(false);
        skinItems.setVisible(true);
        skinItems.setManaged(true);
    }

    public void returnMenu() {
        SCREEN = 0;
        initEvents();
        timer = 50;
        screens.get(1).getGraphicsContext().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        menuItems.setVisible(true);
        menuItems.setManaged(true);
        skinItems.setVisible(false);
        skinItems.setManaged(false);
    }

    public void exitGame() {
        isRunning = false;
        Platform.exit();
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
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void initEvents() {
        canvas.setOnMousePressed(event -> screens.get(SCREEN).onMousePressed(event));
        canvas.setOnMouseClicked(event -> screens.get(SCREEN).onMouseClicked(event));
    }
}