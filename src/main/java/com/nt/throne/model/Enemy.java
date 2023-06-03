package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public abstract class Enemy extends Character {
    public Enemy(Point2D position, Shape hitBox, Image picture, int state, double life, Gun currentGun) {
        super(position, hitBox, picture, state, life, currentGun);
    }
}
