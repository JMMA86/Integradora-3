package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public abstract class Character extends AliveElement implements IAct {
    private Gun currentGun;

    public Character(Point2D position, Shape hitBox, Image picture, int state, double life, Gun currentGun) {
        super(position, hitBox, picture, state, life);
        this.currentGun = currentGun;
    }
}
