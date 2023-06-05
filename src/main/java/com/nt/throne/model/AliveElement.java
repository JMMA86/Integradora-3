package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class AliveElement extends Element {
    private double life;

    public AliveElement(Point2D position, Image picture) {
        super(position, picture);
        this.life = 100.0;
    }

    public abstract void takeDamage();

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }
}
