package com.nt.throne.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class SkinScreen extends BaseScreen {
    private final ArrayList<Image> moving;
    private int imageCounter = 0;

    public SkinScreen(Canvas canvas) {
        super(canvas);
        moving = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Image image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/WalkingBack/animation-" + (i+1) + ".png");
            moving.add(image);
        }
    }

    @Override
    public void paint() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.drawImage(moving.get(imageCounter), 600, 280, 80, 160);
        imageCounter++;
        if (imageCounter == 9) {
            imageCounter = 0;
        }
    }

    @Override
    public void onMousePressed(MouseEvent event) {

    }
}
