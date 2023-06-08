package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ChaserEnemy extends Enemy {
    private Point2D direction;
    private Boolean isMovingRandomly;

    public ChaserEnemy(Point2D position, Image picture) {
        super(position, picture);
        this.direction = new Point2D(0,0);
        this.isMovingRandomly = false;
    }

    @Override
    public void move() {
        setPosition( getPosition().add( direction.getX(), direction.getY() ) );
    }

    @Override
    public void attack() {

    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public Point2D getDirection() {
        return this.direction;
    }

    public void calculateMovement() {

        if (!isMovingRandomly) {
            randomPath();
        }
    }

    public void randomPath() {
        Timer timer = new Timer();
        TimerTask moveRandom = new TimerTask() {
            @Override
            public void run() {
                isMovingRandomly = false;
                Point2D heroPosition = Hero.getInstance().getPosition();
                Point2D key1 = new Point2D( getPosition().getX(), heroPosition.getY() );
                Point2D key2 = new Point2D(heroPosition.getX(), getPosition().getY());
                setDirection(
                    calcUnitVector( (new Random()).nextInt(2) == 0 ? key1 : key2 )
                );
                setDirection(new Point2D( getDirection().getX()*4, getDirection().getY()*4 ));
            }
        };

        timer.schedule(moveRandom, 3000);
    }
}
