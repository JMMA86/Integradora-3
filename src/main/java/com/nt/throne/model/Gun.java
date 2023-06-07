package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class Gun extends Element {

    private int numShots;
    private int ammo;
    private Random random;
    private final int CHARGER_SIZE;
    private long delay;

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

    public abstract void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest);

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public int getNumShots() {
        return numShots;
    }

    public void setNumShots(int numShots) {
        this.numShots = numShots;
    }
}
