package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShotGun extends Gun {
    private double spreadAngle;
    private final double minAngle;
    private final double maxAngle;
    public ShotGun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture, CHARGER_SIZE);
        setHitBox(
            new Rectangle(
                position.getX(),
                position.getY(),
                picture.getWidth() / 2,
                picture.getHeight() / 2
            )
        );
        setDelay(300);
        setBulletsPerShoot(8);
        setRechargeTime(2000);
        minAngle = Math.toRadians(-60);
        maxAngle = Math.toRadians(60);
    }

    @Override
    public void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest) {
        setAmmo(getAmmo() - getBulletsPerShoot());
        Timer timer = new Timer();
        if (getAmmo() > 0) {
            List<Bullet> bulletsBuffer = new ArrayList<>();
            for (int i = 0; i < getBulletsPerShoot(); i++) {
                spreadAngle = getRandom().nextDouble() * (maxAngle - minAngle) + minAngle;
                double angle = Math.toRadians(i * spreadAngle - (spreadAngle * (getBulletsPerShoot() - 1)) / 2.0);
                Point2D dispersedDest = calcUnitVectorWithSpread(dest, angle);
                bulletsBuffer.add(new Bullet(
                    getPosition(),
                    dispersedDest,
                    15,
                    30,
                    new Image(
                        System.getProperty("user.dir") +
                            "/src/main/resources/com/nt/throne/Guns/bullet.png"
                    )
                ));
            }
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    gameBullets.addAll(bulletsBuffer);
                }
            };

            timer.schedule(task, getDelay());

        } else {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    setAmmo(getCHARGER_SIZE());
                }
            };

            timer.schedule(task, getRechargeTime());
        }
    }

    @Override
    public void paint(GraphicsContext context) {
        context.drawImage(
            getPicture(),
            getPosition().getX(),
            getPosition().getY(),
            getPicture().getWidth() / 2,
            getPicture().getHeight() / 2
        );
    }

    private Point2D calcUnitVectorWithSpread(Point2D target, double spreadAngle) {
        double dx = target.getX() - getPosition().getX();
        double dy = target.getY() - getPosition().getY();
        double length = Math.sqrt(dx * dx + dy * dy);

        double spreadOffset = Math.tan(spreadAngle);
        double offsetX = dx + spreadOffset * length;
        double offsetY = dy + spreadOffset * length;

        double normalizedX = offsetX / length;
        double normalizedY = offsetY / length;

        return new Point2D(normalizedX, normalizedY);
    }
}
