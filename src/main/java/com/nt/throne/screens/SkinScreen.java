package com.nt.throne.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class SkinScreen extends BaseScreen {
    private static int skinSelected = 0;
    private final ArrayList<Image> moving;
    private final Image leftArrow;
    private final Image leftArrowSelected;
    private final Image rightArrow;
    private final Image rightArrowSelected;
    private boolean leftSelected;
    private boolean rightSelected;
    private int imageCounter = 0;

    public SkinScreen(Canvas canvas) {
        super(canvas);
        moving = new ArrayList<>();
        leftArrow = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/leftArrow.png");
        leftArrowSelected = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/leftArrowSelected.png");
        rightArrow = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/rightArrow.png");
        rightArrowSelected = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Menu/rightArrowSelected.png");
        leftSelected = false;
        rightSelected = false;
        //Skin charging
        moving.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero1.png"));
        moving.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero2.png"));
        moving.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png"));
    }

    public static int getSkinSelected() {
        return skinSelected;
    }

    @Override
    public void paint() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (!leftSelected) {
            graphicsContext.drawImage(leftArrow, 470, 320, 62, 62);
        } else {
            graphicsContext.drawImage(leftArrowSelected, 470, 320, 62, 62);
        }
        if (!rightSelected) {
            graphicsContext.drawImage(rightArrow, 750, 320, 62, 62);
        } else {
            graphicsContext.drawImage(rightArrowSelected, 750, 320, 62, 62);
        }
        //Trim configuration
        int frameWidth = 64; //Width of frame on image
        int frameHeight = 64; //Height of frame on image
        int row = 10; //Animation row on spriteSheet
        int frameLimiter = 9; //Frame limiter
        graphicsContext.drawImage(moving.get(skinSelected), frameWidth * imageCounter, row * frameHeight, frameHeight, frameWidth, 580, 280, frameWidth * 2, frameHeight * 2);
        imageCounter++;
        if (imageCounter == frameLimiter) {
            imageCounter = 0;
        }
    }

    @Override
    public void onMousePressed(MouseEvent event) {

    }

    @Override
    public void onMouseReleased(MouseEvent event) {

    }

    @Override
    public void onMouseDragged(MouseEvent event) {

    }

    @Override
    public void onMouseClicked(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        if (mouseX > 467 && mouseX < 529 && mouseY > 110 && mouseY < 171) {
            skinSelected--;
        }
        if (mouseX > 750 && mouseX < 808 && mouseY > 110 && mouseY < 171) {
            skinSelected++;
        }
        if (skinSelected == moving.size()) {
            skinSelected = 0;
        }
        if (skinSelected == -1) {
            skinSelected = moving.size() - 1;
        }
    }

    @Override
    public void onMouseMoved(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        leftSelected = mouseX > 467 && mouseX < 529 && mouseY > 110 && mouseY < 171;
        rightSelected = mouseX > 750 && mouseX < 808 && mouseY > 110 && mouseY < 171;
    }

    @Override
    public void onKeyPressed(KeyEvent event) {

    }

    @Override
    public void onKeyReleased(KeyEvent event) {

    }
}
