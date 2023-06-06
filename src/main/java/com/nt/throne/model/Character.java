package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Character extends AliveElement implements IAct {
    private Gun currentGun;
    private int currentFrame;
    private int currSprite;
    private boolean movementLocked = false;

    public Character(Point2D position, Image picture) {
        super(position, picture);
        this.currentGun = null;
        this.currSprite = 10;
        this.currentFrame = 0;
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    public void setCurrentGun(Gun currentGun) {
        this.currentGun = currentGun;
    }

    @Override
    public void paint(GraphicsContext context) {
        int frameWidth = 64, frameHeight = 64;
        move();
        setHitBox(new Rectangle(getPosition().getX() - 16, getPosition().getY() - 32, 32, 64));

        switch (getState()) {
            case 0 -> currentFrame = 0;
            case 1 -> currSprite = 10;
            case 2 -> currSprite = 8;
            case 3 -> currSprite = 9;
            case 4 -> currSprite = 11;
        }

        context.drawImage( getPicture(),
                currentFrame*frameWidth, currSprite*frameHeight,
                frameWidth, frameHeight,
                getPosition().getX() - frameWidth, getPosition().getY() - frameHeight,
                frameWidth*2, frameHeight*2);

        if(getState() != 0) currentFrame++;
        if(currentFrame % 9 == 0) currentFrame = 0;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public void lockMovement(boolean state) {
        movementLocked = state;
    }

    public boolean isMovementLocked() {
        return movementLocked;
    }
}
