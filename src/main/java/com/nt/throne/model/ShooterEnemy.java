package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class ShooterEnemy extends Enemy {
    public ShooterEnemy(Point2D position, Shape hitBox, Image picture, int state, double life, Gun currentGun) {
        super(position, hitBox, picture, state, life, currentGun);
    }

    @Override
    public void takeDamage() {

    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
