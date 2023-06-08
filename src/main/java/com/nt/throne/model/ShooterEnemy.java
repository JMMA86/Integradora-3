package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class ShooterEnemy extends Enemy {
    public ShooterEnemy(Point2D position, Image picture) {
        super(position, picture);
        setLife(100);
        setInvulnerability(100);
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
