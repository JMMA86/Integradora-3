package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends Character {
    private Point2D direction;
    private Boolean lockDirectionChange;

    public Enemy(Point2D position, Image picture) {
        super(position, picture);
        this.direction = new Point2D(-1, -1);
        this.lockDirectionChange = false;
    }

    public Point2D whichIsCloser(Point2D v1, Point2D v2) {
        Point2D target = Hero.getInstance().getPosition();
        Point2D p1 = getPosition().add(v1.getX(), v1.getY());
        Point2D p2 = getPosition().add(v2.getX(), v2.getY());

        double d1x = Math.abs(p1.getX() - target.getX()), d1y = Math.abs(p1.getY() - target.getY());
        double d2x = Math.abs(p2.getX() - target.getX()), d2y = Math.abs(p2.getY() - target.getY());

        if (Math.sqrt(Math.pow(d1x, 2) + Math.pow(d1y, 2)) > Math.sqrt(Math.pow(d2x, 2) + Math.pow(d2y, 2))) {
            return v1;
        } else {
            return v2;
        }
    }

    public void calculateMovement() {
        Point2D target = Hero.getInstance().getPosition();
        boolean rotate = false;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                lockDirectionChange = false;
            }
        };


        if (!lockDirectionChange) {
            Point2D direction = calcUnitVector(target);
            setDirection(direction);
            setDirection(new Point2D(getDirection().getX() * 4, getDirection().getY() * 4));
            setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
        }

        if (checkBlockCollision(0)) {
            setPosition(getPosition().add(0, 8));
            rotate = true;
        }
        if (checkBlockCollision(1)) {
            setPosition(getPosition().add(0, -8));
            rotate = true;
        }
        if (checkBlockCollision(2)) {
            setPosition(getPosition().add(8, 0));
            rotate = true;
        }
        if (checkBlockCollision(3)) {
            setPosition(getPosition().add(-8, 0));
            rotate = true;
        }


        if (rotate) {
            Point2D v1 = new Point2D((-1) * getDirection().getY(), getDirection().getX());
            Point2D v2 = new Point2D(getDirection().getY(), getDirection().getX() * (-1));
            setDirection(whichIsCloser(v1, v2));
            lockDirectionChange = true;
            timer.schedule(task, 100);
            return;
        } else {
            setPosition(getPosition().add(-getDirection().getX(), -getDirection().getY()));
        }


        if (getDirection().getY() < 0) {
            setState(1);
        } else {
            setState(2);
        }
        if (getDirection().getX() < 0) {
            setState(3);
        } else {
            setState(4);
        }
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }
}
