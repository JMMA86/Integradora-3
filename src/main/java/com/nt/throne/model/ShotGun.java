package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

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
    }

    @Override
    public int onShot() {
        return 0;
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
