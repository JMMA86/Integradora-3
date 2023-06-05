package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Bullet extends Element implements IAct {

    public Bullet(Point2D position, Image picture) {
        super(position, picture);
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
