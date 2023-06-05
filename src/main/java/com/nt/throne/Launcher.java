package com.nt.throne;

import com.nt.throne.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        renderView("main-menu-view.fxml", 1280, 720);
    }

    public void renderView(String fxml, int width, int height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            primaryStage.setTitle("Nuclear Throne");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(windowEvent -> {
                MainMenuController controller = fxmlLoader.getController();
                controller.setRunning(false);

            });
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}