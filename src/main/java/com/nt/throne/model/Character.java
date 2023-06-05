package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Character extends AliveElement implements IAct {
    private Gun currentGun;
    private int currentFrame;

    public Character(Point2D position, Image picture) {
        super(position, picture);
        this.currentGun = null;
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
        switch (getState()) {
            case 0 -> context.drawImage( getPicture(),
                    currentFrame*frameWidth, 10*frameHeight,
                    frameWidth, frameHeight,
                    getPosition().getX(), getPosition().getY(),
                    frameWidth*2, frameHeight*2);
        }
        currentFrame++;
        if(currentFrame % 9 == 0) currentFrame = 0;
    }
}
