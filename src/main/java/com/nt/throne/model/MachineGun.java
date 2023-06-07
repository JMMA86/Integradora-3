package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

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
    public int onShot() {
        setAmmo(getAmmo() - 5);
        if (getAmmo() > 0) {
            return 5;
        }
        return getAmmo();
    }
}
