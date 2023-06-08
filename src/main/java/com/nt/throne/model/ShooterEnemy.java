package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class ShooterEnemy extends Enemy {
    private Point2D focus;

    public ShooterEnemy(Point2D position, Image picture) {
        super(position, picture);
        setLife(100);
        setInvulnerability(100);
    }

    public void setFocus(Point2D dest) {
        focus = dest;
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }

    public Point2D getFocus() {
        return focus;
    }
}
