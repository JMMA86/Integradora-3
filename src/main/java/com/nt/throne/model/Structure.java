package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Structure extends AliveElement {
    public Structure(Point2D position, Image image) {
        super(position, image);
        setLife(500);
        setHitBox( new Rectangle(getPosition().getX(), getPosition().getY() - 30, 68, 68) );
    }

    @Override
    public void takeDamage(Element origin) {
        if (origin instanceof Bullet) {
            setLife(getLife() - ((Bullet) origin).getDamage());
        }
    }
}
