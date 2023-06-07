package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Gun extends Element {
    private int ammo;
    private Random random;
    private final int CHARGER_SIZE;

    public Gun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture);
        this.CHARGER_SIZE = CHARGER_SIZE;
        this.ammo = CHARGER_SIZE;
        this.random = new Random();
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
