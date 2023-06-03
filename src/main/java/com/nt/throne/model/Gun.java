package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Gun extends Element {
    private int ammo;
    private final int CHARGER_SIZE;

    public Gun(Point2D position, Shape hitBox, Image picture, int state, int CHARGER_SIZE) {
        super(position, hitBox, picture, state);
        this.CHARGER_SIZE = CHARGER_SIZE;
    }
}
