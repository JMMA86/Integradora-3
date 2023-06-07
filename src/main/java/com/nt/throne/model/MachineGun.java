package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class MachineGun extends Gun{
    public MachineGun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture, CHARGER_SIZE);
        setHitBox(
            new Rectangle(
                position.getX(),
                position.getY(),
                picture.getWidth() / 2,
                picture.getHeight() / 2
            )
        );
        setDelay(60);
        setBulletsPerShoot(5);
        setRechargeTime(2000);
    }

    @Override
    public void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest) {
        setAmmo(getAmmo() - getBulletsPerShoot());
        Timer timer = new Timer();
        if (getAmmo() > 0) {
            for (int i = 0; i < getBulletsPerShoot(); i++) {
                final int shotIndex = i;
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        gameBullets.add(
                            new Bullet(
                                getPosition(),
                                calcUnitVector(
                                    dest
                                ), 8.5, 30,
                                new Image(
                                    System.getProperty("user.dir") +
                                        "/src/main/resources/com/nt/throne/Guns/bullet.png"
                                )
                            )
                        );

                        if (shotIndex == getNumShots() - 1) {
                            setNumShots(getNumShots() - 1);
                            timer.cancel();
                            timer.purge();
                        }
                    }
                };

                timer.schedule(task, (long) i * getDelay());
            }
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
}
