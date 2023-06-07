package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Element {
    private Point2D position;
    private Shape hitBox;
    private Image picture;
    private int state;


    public Element(Point2D position, Image picture) {
        this.position = position;
        this.state = 0;
        this.picture = picture;
        this.hitBox = new Rectangle(position.getX(), position.getY(), picture.getWidth(), picture.getHeight());
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    public void setHitBox(Shape hitBox) {
        this.hitBox = hitBox;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isColliding(Element element) {
        return getHitBox().intersects(element.getHitBox().getBoundsInParent());
    }

    public Point2D calcUnitVector(Point2D dest) {
        double deltaX = dest.getX() - position.getX();
        double deltaY = dest.getY() - position.getY();
        double magnitude = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return new Point2D(deltaX / magnitude, deltaY / magnitude);
    }

    public void paint(GraphicsContext context) {
        context.drawImage(this.picture, this.position.getX(), this.position.getY());
    }
}
