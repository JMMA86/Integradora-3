package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShotGun extends Gun {
    private final double minAngle;
    private final double maxAngle;
    private boolean canShot;
    private final MediaPlayer shotSound;
    private final MediaPlayer reloadSound;

    public ShotGun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture, CHARGER_SIZE);
        shotSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/shotgunShot.mp3").toURI().toString()));
        reloadSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/reload.mp3").toURI().toString()));
        shotSound.setVolume(0.5);
        reloadSound.setVolume(0.5);
        setHitBox(
            new Rectangle(
                position.getX(),
                position.getY(),
                picture.getWidth() / 2,
                picture.getHeight() / 2
            )
        );
        setDelay(300);
        setBulletsPerShoot(5);
        setRechargeTime(2000);
        setDamage(15);
        minAngle = Math.toRadians(-90);
        maxAngle = Math.toRadians(90);
        canShot = true;
    }

    @Override
    public void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest) {
        if (getAmmo() > 0) {
            if (canShot) {
                setAmmo(getAmmo() - getBulletsPerShoot());
                List<Bullet> bulletsBuffer = new ArrayList<>();
                canShot = false;
                for (int i = 0; i < getBulletsPerShoot(); i++) {
                    double spreadAngle = getRandom().nextDouble() * (maxAngle - minAngle) + minAngle;
                    double angle = Math.toRadians(i * spreadAngle - (spreadAngle * (getBulletsPerShoot() - 1)) / 2.0);
                    Point2D dispersedDest = calcUnitVectorWithSpread(dest, angle);
                    bulletsBuffer.add(new Bullet(
                        getEnd(),
                        dispersedDest,
                        getDamage(),
                        30,
                        new Image(
                            System.getProperty("user.dir") +
                                "/src/main/resources/com/nt/throne/Guns/bullet.png"
                        )
                    ));
                }
                gameBullets.addAll(bulletsBuffer);
                startDelayShotTimer();
            }
        } else {
            rechargeTimer();
        }
    }

    public void startDelayShotTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                canShot = true;
            }
        };
        timer.schedule(task, getDelay());
    }

    public void rechargeTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (getAmmo() <= 0 && getAmmo() != 35) {
                    setAmmo(getCHARGER_SIZE());
                    reloadSound.stop();
                    reloadSound.seek(Duration.ZERO);
                    reloadSound.play();
                }
            }
        };
        timer.schedule(task, getRechargeTime());
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

    @Override
    public MediaPlayer getShotSound() {
        return shotSound;
    }

    @Override
    public MediaPlayer getReloadSound() {
        return reloadSound;
    }
}
