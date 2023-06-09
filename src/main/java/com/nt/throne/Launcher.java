package com.nt.throne;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.controller.MainMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    private static Stage primaryStage;
    public static void renderView(String fxml, int width, int height) {
        try {
            Image icon = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/icon.png");
            primaryStage.getIcons().add(icon);
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            primaryStage.setTitle("Nuclear Throne");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(windowEvent -> {
                try {
                    MainMenuController controller = fxmlLoader.getController();
                    controller.setRunning(false);
                } catch (ClassCastException e) {
                    try {
                        InGameViewController controller1 = fxmlLoader.getController();
                        controller1.setRunning(false);
                    } catch (ClassCastException f) {
                        Platform.exit();
                    }
                    Platform.exit();
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Launcher.primaryStage = primaryStage;
        renderView("main-menu-view.fxml", 1280, 720);
    }
}