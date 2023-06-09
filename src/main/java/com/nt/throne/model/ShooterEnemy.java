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
        setActualGun(new ShotGun(
            getPosition(),
            new Image(
                System.getProperty("user.dir") +
                    "/src/main/resources/com/nt/throne/Guns/shotgun.png"
            ),
            20
        ));

        getActualGun().setDamage(5);

        keep = true;
    }

    @Override
    public void move() {
    }

    public void moveAndShot(Circle collidingElement, CopyOnWriteArrayList<Bullet> gameBullets) {
        Point2D target = Hero.getInstance().getPosition();
        if (keep) {
            calculateMovement();
            setPosition(getPosition().add(getDirection().getX(), getDirection().getY()));
            // - actualGun.getPicture().getWidth() / 10
            actualGun.setPosition(new Point2D(getPosition().getX() , getPosition().getY()));
        }

        if (directPath(target) || getHitBox().intersects(collidingElement.getBoundsInParent())) {
            setState(getState());
            if( directPath(target) ) {
                actualGun.onShot(gameBullets, Hero.getInstance().getPosition());
                keep = false;
            }
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
        actualGun.setPosition(new Point2D(getPosition().getX() - actualGun.getPicture().getWidth() / 10 , getPosition().getY()));
    }
}
