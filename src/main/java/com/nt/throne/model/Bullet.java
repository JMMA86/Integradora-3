package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Bullet extends Element implements IAct {
    public Bullet(Point2D position, Shape hitBox, Image picture, int state) {
        super(position, hitBox, picture, state);
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
