package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.screens.Scenario;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.CopyOnWriteArrayList;

public class ChaserEnemy extends Enemy {
    private Point2D direction;
    private Boolean movingY;

    public ChaserEnemy(Point2D position, Image picture) {
        super(position, picture);
        this.direction = new Point2D(0,-4);
        this.movingY = true;
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
        Point2D target = Hero.getInstance().getPosition();
        /*
                if( !isThereIntersection(getPosition(), target)) {
            Point2D direction = calcUnitVector( target );
            setDirection( direction );
            setDirection(new Point2D( getDirection().getX()*4, getDirection().getY()*4 ));
            return;
        }

        if(checkBlockCollision(0)) {
            setPosition(getPosition().add(0, 8));
            setMovingY(false);
        }
        if(checkBlockCollision(1)) {
            setPosition(getPosition().add(0, -8));
            setMovingY(false);
        }
        if (checkBlockCollision(2)) {
            setPosition(getPosition().add(8, 0));
            setMovingY(true);
        }
        if(checkBlockCollision(3)) {
            setPosition(getPosition().add(-8, 0));
            setMovingY(true);
        }

        // check collisions with the map
        if( getPosition().getY() < Scenario.getLimitY()[0] + 32 || getPosition().getY() > Scenario.getLimitY()[1] - 32 || checkBlockCollision(0) || checkBlockCollision(1) ) {
            setMovingY(false);
        }
        if( getPosition().getX() < Scenario.getLimitX()[0] + 16 || getPosition().getX() > Scenario.getLimitX()[1] - 16 || checkBlockCollision(2) || checkBlockCollision(3) ) {
            setMovingY(true);
        }

        if(movingY && !isThereIntersection(getPosition(), new Point2D(target.getX(), getPosition().getY())) ) {
            movingY = false;
            double xDir = target.getX() < getPosition().getX() ? -4 : 4;
            setDirection(new Point2D(xDir, 0));
        }

        if(!movingY && !isThereIntersection( getPosition(), new Point2D(getPosition().getX(), target.getY()) )) {
            movingY = true;
            double yDir = target.getY() < getPosition().getY() ? -4 : 4;
            setDirection(new Point2D(0, yDir));
        }
         */


    }

    private void setMovingY(boolean movingY) {
        Point2D target = Hero.getInstance().getPosition();
        if(movingY) {
            double yDir = target.getY() < getPosition().getY() ? -4 : 4;
            setDirection(new Point2D(0, 4));
        } else {
            double xDir = target.getX() < getPosition().getX() ? -4 : 4;
            setDirection(new Point2D(4, 0));
        }
    }

    /*
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
         */

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
