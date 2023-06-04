package com.nt.throne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        renderView("main-menu-view.fxml", 1280, 720);
    }

    public FXMLLoader renderView(String fxml, int width, int height) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml));
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Nuclear Throne");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}