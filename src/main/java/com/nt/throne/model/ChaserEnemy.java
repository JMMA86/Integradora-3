package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.CopyOnWriteArrayList;

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

        /*

                boolean rx = false, ry = false;

        if(rx || ry) {
            if(rx) {
                setDirection(getDirection().add(0, 4));
            }
            if(ry) {
                setDirection(getDirection().add(4, 0));
            }
            isMovingRandomly = true;
            return;
        }

        Point2D target = Hero.getInstance().getPosition();
        Random rnd = new Random();
        if( !isThereIntersection(getPosition(), target)) {
            if(!isMovingRandomly) {
                isMovingRandomly = true;
                target = new Point2D(
                    rnd.nextInt(0, 1280), rnd.nextInt(0, 720)
                );
            }
        } else {
            isMovingRandomly = false;
        }
        Point2D direction = calcUnitVector( target );
        setDirection( direction );
         */

        if(checkBlockCollision(0)) {
            setPosition(getPosition().add(0, 8));
            setDirection(new Point2D(0,0));
        }
        if (checkBlockCollision(2)) {
            setPosition(getPosition().add(8, 0));
            setDirection(new Point2D(0,0));
        }
        if(checkBlockCollision(3)) {
            setPosition(getPosition().add(-8, 0));
            setDirection(new Point2D(0,0));
        }
        if(checkBlockCollision(1)) {
            setPosition(getPosition().add(0, -8));
            setDirection(new Point2D(0,0));
        }

        Point2D target = Hero.getInstance().getPosition();

        if( !isThereIntersection(getPosition(), target)) {
            Point2D direction = calcUnitVector( target );
            setDirection( direction );
            setDirection(new Point2D( getDirection().getX()*4, getDirection().getY()*4 ));
        }

        if(getDirection().getY() < 0) {
            setState(1);
        } else {
            setState(2);
        }
        if(getDirection().getX() < 0) {
            setState(3);
        } else {
            setState(4);
        }

    }

    private boolean isThereIntersection(Point2D position, Point2D target) {
        double m = (position.getY() - target.getY())/(position.getX() - target.getX());
        double b = position.getY() - m * position.getX();
        CopyOnWriteArrayList<Structure> blocks = InGameViewController.getScreens().get(InGameViewController.getSCREEN()).getStructures();
        for(Structure s : blocks) {
            Rectangle enemyHitBox = new Rectangle(s.getPosition().getX() - 16, m * (s.getPosition().getX() - 32) + b,32, 64);
            if(s.getHitBox().intersects(enemyHitBox.getBoundsInParent())) return true;
        }
        return false;
    }

    /*
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
     */


}
