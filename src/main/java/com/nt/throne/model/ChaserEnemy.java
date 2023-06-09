package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.screens.Scenario;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChaserEnemy extends Enemy {
    private Point2D direction;
    private Boolean movingY;
    private boolean lockDirectionChange;

    public ChaserEnemy(Point2D position, Image picture) {
        super(position, picture);
        this.direction = new Point2D(0,-4);
        this.movingY = true;
        this.lockDirectionChange = false;
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

        Point2D rotation = null;
        boolean rotate = false;


        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                lockDirectionChange = false;
            }
        };




        if(!lockDirectionChange) {
            Point2D direction = calcUnitVector( target );
            setDirection( direction );
            setDirection(new Point2D( getDirection().getX()*4, getDirection().getY()*4 ));
            setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
        }

        if(checkBlockCollision(0)) {
            setPosition(getPosition().add(0, 8));
            rotate = true;
            rotation = new Point2D(4, 0);
        }
        if(checkBlockCollision(1)) {
            setPosition(getPosition().add(0, -8));
            rotate = true;
            rotation = new Point2D(4, 0);
        }
        if (checkBlockCollision(2)) {
            setPosition(getPosition().add(8, 0));
            rotate = true;
            rotation = new Point2D(0, 4);
        }
        if(checkBlockCollision(3)) {
            setPosition(getPosition().add(-8, 0));
            rotate = true;
            rotation = new Point2D(0, 4);
        }


        if(rotate) {
            Point2D v1 = new Point2D( (-1)* getDirection().getY(), getDirection().getX());
            Point2D v2 = new Point2D( getDirection().getY(), getDirection().getX()*(-1));
            setDirection( whichIsCloser(v1, v2) );
            lockDirectionChange = true;
            timer.schedule(task, 100);
            return;
        } else {
            setPosition(getPosition().add(-getDirection().getX(), -getDirection().getY()));
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

        /*
        if( !isThereIntersection(getPosition(), target)) {
            Point2D direction = calcUnitVector( target );
            setDirection( direction );
            setDirection(new Point2D( getDirection().getX()*4, getDirection().getY()*4 ));
            return;
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

    private Point2D whichIsCloser(Point2D v1, Point2D v2) {
        Point2D target = Hero.getInstance().getPosition();
        Point2D p1 = getPosition().add(v1.getX(), v1.getY());
        Point2D p2 = getPosition().add(v2.getX(), v2.getY());

        double d1x = Math.abs( p1.getX() - target.getX() ), d1y = Math.abs( p1.getY() - target.getY() );
        double d2x = Math.abs( p2.getX() - target.getX() ), d2y = Math.abs( p2.getY() - target.getY() );

        if( Math.sqrt( Math.pow(d1x, 2) + Math.pow(d1y,2) ) > Math.sqrt( Math.pow(d2x, 2) + Math.pow(d2y, 2) ) ) {
            return v1;
        } else {
            return v2;
        }
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
