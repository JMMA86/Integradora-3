package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShotGun extends Gun{
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
        setDelay(150);
    }

    @Override
    public void onShot(CopyOnWriteArrayList<Bullet> gameBullets, Point2D dest) {

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
