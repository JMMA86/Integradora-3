package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Bullet extends Element implements IAct {
    private Point2D direction;

    private int aceleration;

    public Bullet(int aceleration, Point2D position, Point2D direction, Image picture) {
        super(position, picture);
        this.direction = direction;
        this.aceleration = aceleration;
    }

    @Override
    public void move() {
        setPosition(getPosition().add(direction.getX() * aceleration, direction.getY() * aceleration));
    }

    @Override
    public void attack() {

    }
}
