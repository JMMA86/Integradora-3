package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShooterEnemy extends Enemy {
    private Gun actualGun;

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
    }

    @Override
    public void move() {
        setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
    }

    public void moveAndShot(Circle collidingElement, CopyOnWriteArrayList<Bullet> gameBullets) {
        actualGun.setPosition(new Point2D(getPosition().getX()-actualGun.getPicture().getWidth()/4, getPosition().getY()));
        if (!getHitBox().intersects(collidingElement.getBoundsInParent())) {
            calculateMovement();
        } else {
            //actualGun.onShot(gameBullets,focus);
        }
    }

    @Override
    public void attack() {

    }

    public Gun getActualGun() {
        return actualGun;
    }

    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
    }
}
