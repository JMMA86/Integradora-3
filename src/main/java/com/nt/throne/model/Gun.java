package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Gun extends Element {
    private final int CHARGER_SIZE;
    private int numShots;
    private int ammo;
    private Random random;
    private Point2D end;
    private long delay;
    private double damage;
    private int bulletsPerShoot;
    private long rechargeTime;
    private MediaPlayer shotSound;
    private MediaPlayer reloadSound;

    public Gun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture);
        this.CHARGER_SIZE = CHARGER_SIZE;
        this.ammo = CHARGER_SIZE;
        this.random = new Random();
        this.damage = 10;
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

    public int getBulletsPerShoot() {
        return bulletsPerShoot;
    }

    public void setBulletsPerShoot(int bulletsPerShoot) {
        this.bulletsPerShoot = bulletsPerShoot;
    }

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public MediaPlayer getShotSound() {
        return shotSound;
    }

    public MediaPlayer getReloadSound() {
        return reloadSound;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
