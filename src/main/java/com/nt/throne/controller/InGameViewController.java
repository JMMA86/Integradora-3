package com.nt.throne.controller;

import com.nt.throne.screens.BaseScreen;
import com.nt.throne.screens.Pantheon;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InGameViewController implements Initializable {

    public VBox statsBox;
    public Text nameTxt;
    public Text lifeTxt;
    public Text ammoTxt;
    public Canvas canvas;

    private boolean isRunning;
    private ArrayList<BaseScreen> screens;
    //Screens:
    /*
    0: pantheon
     */
    public static int SCREEN = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.screens = new ArrayList<>();
        screens.add( new Pantheon(canvas));
        isRunning = true;

        new Thread( () -> {
            while (isRunning){
                Platform.runLater(this::paint);
                Platform.runLater(this::run);
                pause(50);
            }
        }).start();

        initEvents();
    }

    public void paint(){
        if (SCREEN <= screens.size()) screens.get(SCREEN).paint();
    }

    public void run() {
        if (SCREEN <= screens.size()) {
            if (screens.get(SCREEN) instanceof Pantheon) {
                ((Pantheon) screens.get(SCREEN)).run();
            }
        }
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
        canvas.setOnMouseClicked(event -> screens.get(SCREEN).onMouseClicked(event));
        canvas.setOnKeyPressed(event -> screens.get(SCREEN).onKeyPressed(event));
        canvas.setOnKeyReleased(event -> screens.get(SCREEN).onKeyReleased(event));
    }
}
