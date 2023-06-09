package com.nt.throne.controller;

import com.nt.throne.Launcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGameViewController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private MediaView videoBackground;
    @FXML
    private Text titleScreen;
    @FXML
    private Text backBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Fonts
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Fonts/upheavtt.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font customFont = Font.loadFont(inputStream, 20);
        titleScreen.setFont(customFont);
        backBtn.setFont(customFont);

        backBtn.setOnMouseEntered(event -> backBtn.setOpacity(0.5));
        backBtn.setOnMouseExited(event -> backBtn.setOpacity(1));
    }

    public void backToMenu(MouseEvent event) {
        Launcher.renderView("main-menu-view.fxml", 1280, 720);
    }
}
