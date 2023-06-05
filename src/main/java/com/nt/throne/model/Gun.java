package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Gun extends Element {
    private int ammo;
    private final int CHARGER_SIZE;

    public Gun(Point2D position, ArrayList<ArrayList<Image>> sprites, int CHARGER_SIZE) {
        super(position, sprites);
        this.CHARGER_SIZE = CHARGER_SIZE;
        this.ammo = CHARGER_SIZE;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getCHARGER_SIZE() {
        return CHARGER_SIZE;
    }
}
