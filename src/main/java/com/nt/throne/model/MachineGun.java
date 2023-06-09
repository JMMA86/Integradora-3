package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class MachineGun extends Gun {
    private final MediaPlayer shotSound;
    private final MediaPlayer reloadSound;

    public MachineGun(Point2D position, Image picture, int CHARGER_SIZE) {
        super(position, picture, CHARGER_SIZE);
        shotSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/machinegunShot.mp3").toURI().toString()));
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
        setDelay(60);
        setBulletsPerShoot(1);
        setRechargeTime(2000);
    }

    @Override
    public void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest) {
        setAmmo(getAmmo() - getBulletsPerShoot());
        Timer timer = new Timer();
        if (getAmmo() > 0) {
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
                }
            };

            timer.schedule(task, getDelay());

        } else {
            reloadSound.stop();
            reloadSound.seek(Duration.ZERO);
            reloadSound.play();
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

    @Override
    public MediaPlayer getShotSound() {
        return shotSound;
    }

    @Override
    public MediaPlayer getReloadSound() {
        return reloadSound;
    }
}
