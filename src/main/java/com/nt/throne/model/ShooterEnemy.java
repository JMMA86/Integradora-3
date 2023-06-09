package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShooterEnemy extends Enemy {
    private Gun actualGun;
    private boolean keep;
    public ShooterEnemy(Point2D position, Image picture) {
        super(position, picture);
        setLife(100);
        setInvulnerability(100);
        actualGun = new ShotGun(
            getPosition(),
            new Image(
                System.getProperty("user.dir") +
                    "/src/main/resources/com/nt/throne/Guns/shotgun.png"
            ),
            20
        );
        keep = true;
    }

    @Override
    public void move() {
    }

    public void moveAndShot(Circle collidingElement, CopyOnWriteArrayList<Bullet> gameBullets) {
        /*

         */
        if (keep) {
            calculateMovement();
            setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
            actualGun.setPosition(new Point2D(getPosition().getX()-actualGun.getPicture().getWidth()/4, getPosition().getY()));
        }

        if (getHitBox().intersects(collidingElement.getBoundsInParent())) {
            setState(getState());
            actualGun.onShot(gameBullets, Hero.getInstance().getPosition());
            keep = false;
        } else {
            keep = true;
        }
    }

    @Override
    public void attack(AliveElement element) {

    }

    public Gun getActualGun() {
        return actualGun;
    }

    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
    }
}
