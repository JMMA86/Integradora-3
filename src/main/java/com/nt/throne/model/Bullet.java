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

    public Bullet(Point2D position, Point2D direction, double damage, int acceleration, Image picture) {
        super(position, picture);
        this.direction = direction;
        this.acceleration = acceleration;
        this.damage = damage;
        this.canDamage = true;
    }

    @Override
    public void move() {
        if (!movementLocked)
            setPosition(getPosition().add(direction.getX() * acceleration, direction.getY() * acceleration));
    }

    @Override
    public void attack() {

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
}
