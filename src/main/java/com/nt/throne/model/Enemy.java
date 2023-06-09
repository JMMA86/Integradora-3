package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Enemy extends Character {
    private Point2D direction;
    private Boolean lockDirectionChange;
    private double damage = 10;

    private int lockedDirection = -1;
    private int lastLocked = -1;

    public Enemy(Point2D position, Image picture) {
        super(position, picture);
        this.direction = new Point2D(0, 0);
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

        int step = 4;

        if ( ( lockedDirection == -1 || lockedDirection == 0)) {
            if(checkBlockCollision(0) && lastLocked != 0) {
                lockedDirection = 0;
                setDirection( new Point2D(step* calculateDeviation(target, true), 0) );
            } else if(lockedDirection != -1) {
                setDirection( new Point2D(0, 2*step* calculateDeviation(target, true)) );
                lockedDirection = -1;
                lastLocked = 0;

            }
        }
        if ( (lockedDirection == -1 || lockedDirection == 1)) {
            if(checkBlockCollision(1) && lastLocked != 1) {
                lockedDirection = 1;
                setDirection( new Point2D(step* calculateDeviation(target, true), 0) );
            } else if(lockedDirection != -1) {
                setDirection( new Point2D(0, 2*step* calculateDeviation(target, true)) );
                lockedDirection = -1;
                lastLocked = 1;
            }
        }
        if ( (lockedDirection == -1 || lockedDirection == 2)) {
            if(checkBlockCollision(2) && lastLocked != 2) {
                lockedDirection = 2;
                setDirection( new Point2D(0, step* calculateDeviation(target, false)) );
            }else if(lockedDirection != -1) {
                lockedDirection = -1;
                lastLocked = 2;
            }
        }
        if ( (lockedDirection == -1 || lockedDirection == 3)) {
            if(checkBlockCollision(3) && lastLocked != 3) {
                lockedDirection = 3;
                setDirection( new Point2D(0, step* -calculateDeviation(target, false)) );
            } else if(lockedDirection != -1) {
                setDirection( new Point2D(2*step* calculateDeviation(target, false), 0) );
                lockedDirection = -1;
                lastLocked = 3;
            }
        }

        if (lockedDirection == -1 || directPath(target)) {
            Point2D direction = calcUnitVector(target);
            setDirection(direction);
            setDirection(new Point2D(getDirection().getX() * 4, getDirection().getY() * 4));
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

    private double calculateDeviation(Point2D target, boolean yAxis) {
        if( yAxis ) {
            return target.getY() < getPosition().getY() ? -1 : 1;
        } else {
            return target.getX() < getDirection().getX() ? -1 : 1;
        }
    }

    public boolean directPath(Point2D target) {

        CopyOnWriteArrayList<Structure> blocks = InGameViewController.getScreens().get(InGameViewController.getSCREEN()).getStructures();

        for(Structure block : blocks) {
            Line line = new Line( getPosition().getX(), getPosition().getY(), target.getX(), target.getY() );
            if(line.intersects(block.getHitBox().getBoundsInParent())) return false;
        }

        return true;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public double getDamage() {
        return damage;
    }
}
