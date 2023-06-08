package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ShooterEnemy extends Enemy {
    private boolean canGetDamage;
    private long invulnerability;
    private Point2D focus;

    public ShooterEnemy(Point2D position, Image picture) {
        super(position, picture);
        setLife(100);
        this.canGetDamage = true;
        this.invulnerability = 100;
    }

    public void startInvulnerabilityTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                canGetDamage = true;
            }
        };

        timer.schedule(task, invulnerability);
    }

    @Override
    public void takeDamage(Element origin) {
        if (canGetDamage) {
            if (origin instanceof Bullet) {
                setLife(getLife() - ((Bullet) origin).getDamage());
                canGetDamage = false;
                startInvulnerabilityTimer();
            }
        }
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
