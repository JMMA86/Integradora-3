package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Bullet extends Element implements IAct {
    private Point2D direction;
    private int aceleration;
    private double damage;
    private boolean canDamage;

    public Bullet(Point2D position, Point2D direction, double damage, int acceleration, Image picture) {
        super(position, picture);
        this.direction = direction;
        this.aceleration = acceleration;
        this.damage = damage;
        this.canDamage = true;
    }

    @Override
    public void move() {
        setPosition(getPosition().add(direction.getX() * aceleration, direction.getY() * aceleration));
    }

    @Override
    public void attack() {

    }

    public boolean isHurting(Element element) {
        return getHitBox().intersects(element.getHitBox().getBoundsInParent());
    }

    public double getDamage() {
        return this.damage;
    }
}
