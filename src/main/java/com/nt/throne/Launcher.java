package com.nt.throne;

import com.nt.throne.controller.MainController;
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

    public void renderView(String fxml, int width, int height) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml));
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Nuclear Throne");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setOnCloseRequest(windowEvent -> {
                MainController controller = fxmlLoader.getController();
                controller.setRunning(false);

            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}