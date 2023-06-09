package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Bullet extends Element implements IAct {
    private final Point2D direction;
    private final int acceleration;
    private final double damage;
    private boolean canDamage;

    private boolean movementLocked = false;
    private Point2D origin;
    private Double range;

    public Bullet(Point2D position, Point2D direction, double damage, int acceleration, Image picture) {
        super(position, picture);
        this.direction = direction;
        this.acceleration = acceleration;
        this.damage = damage;
        this.canDamage = true;
        this.origin = position;
        this.range = Double.MAX_VALUE;
    }

    public Bullet(Point2D position, Point2D direction, double damage, int acceleration, Image picture, double range) {
        super(position, picture);
        this.direction = direction;
        this.acceleration = acceleration;
        this.damage = damage;
        this.canDamage = true;
        this.origin = position;
        this.range = range;
    }

    @Override
    public void move() {
        if (!movementLocked)
            setPosition(getPosition().add(direction.getX() * acceleration, direction.getY() * acceleration));
    }

    @Override
    public void attack(AliveElement target) {

    }

    public boolean isHurting(Element element) {
        return isColliding(element);
    }

    public double getDamage() {
        return this.damage;
    }

    @Override
    public void paint(GraphicsContext context) {
        move();
        setHitBox(new Circle(getPosition().getX(), getPosition().getY(), 20));
        context.drawImage(getPicture(), getPosition().getX(), getPosition().getY(), 20, 20);
    }

    public boolean isOutOfRange() {
        double dist = Math.sqrt( Math.pow(getPosition().getX() - origin.getX(),2) + Math.pow( getPosition().getY() - origin.getY() ,2 ) );
        return dist > range;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }
}
