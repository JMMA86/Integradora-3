package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShooterEnemy extends Enemy {
    private Point2D focus;
    private Point2D checker;
    private int acceleration;
    private Gun actualGun;
    private Point2D direction;
    private Boolean canShoot;

    public ShooterEnemy(Point2D position, Image picture) {
        super(position, picture);
        setLife(100);
        setInvulnerability(100);
        acceleration = 4;
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

    }

    public void moveAndShot(Circle collidingElement, CopyOnWriteArrayList<Bullet> gameBullets) {
        if (focus != null) {
            if (!getHitBox().intersects(collidingElement.getBoundsInParent())) {
                direction = calcUnitVector(focus);
                Point2D newPosition = new Point2D(
                    getPosition().getX() + direction.getX() * acceleration,
                    getPosition().getY() + direction.getY() * acceleration
                );
                setPosition(newPosition);
                actualGun.setPosition(getPosition());
            } else {
                actualGun.onShot(gameBullets,focus);
            }
        }
    }

    @Override
    public void attack() {

    }

    public Point2D getFocus() {
        return focus;
    }

    public void setFocus(Point2D dest) {
        focus = dest;
    }

    public Gun getActualGun() {
        return actualGun;
    }

    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
    }
}
