package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class ChaserEnemy extends Enemy {
    public ChaserEnemy(Point2D position, Image picture) {
        super(position, picture);
    }

    @Override
    public void move() {
        setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
    }

    @Override
    public void attack() {

    }
}
