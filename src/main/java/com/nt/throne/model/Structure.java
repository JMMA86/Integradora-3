package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Structure extends AliveElement {
    public Structure(Point2D position, Image image) {
        super(position, image);
        setLife(1500);
        setHitBox( new Rectangle(getPosition().getX()-15, getPosition().getY() - 30, 68, 96) );
    }

    @Override
    public void takeDamage(Element origin) {
        if (origin instanceof Bullet) {
            setLife(getLife() - ((Bullet) origin).getDamage());
        }
    }
}
