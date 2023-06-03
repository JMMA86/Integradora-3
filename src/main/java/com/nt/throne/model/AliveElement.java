package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public abstract class AliveElement extends Element {
    private double life;

    public AliveElement(Point2D position, Shape hitBox, Image picture, int state, double life) {
        super(position, hitBox, picture, state);
        this.life = life;
    }

    public abstract void takeDamage();
}
