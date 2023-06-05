package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Character extends AliveElement implements IAct {
    private Gun currentGun;

    public Character(Point2D position, ArrayList<ArrayList<Image>> sprites) {
        super(position, sprites);
        this.currentGun = null;
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    public void setCurrentGun(Gun currentGun) {
        this.currentGun = currentGun;
    }
}
