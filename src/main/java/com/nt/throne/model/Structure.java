package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Structure extends AliveElement {
    public Structure(Point2D position, Shape hitBox, Image picture, int state, double life) {
        super(position, hitBox, picture, state, life);
    }

    @Override
    public void takeDamage() {

    }
}
