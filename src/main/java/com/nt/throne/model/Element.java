package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Element {
    private Point2D position;
    private Shape hitBox;
    private Image picture;
    private int state;
    private ArrayList<ArrayList<Image>> sprites;


    public Element(Point2D position, ArrayList<ArrayList<Image>> sprites) {
        this.position = position;
        this.state = 0;
        this.sprites = sprites;
        this.picture = sprites.get(0).get(0);
        this.hitBox = new Rectangle(picture.getWidth(), picture.getHeight());
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


    public ArrayList<ArrayList<Image>> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<ArrayList<Image>> sprites) {
        this.sprites = sprites;
    }

    public void paint(GraphicsContext context) {
        context.drawImage(this.picture, this.position.getX(), this.position.getY());
    }
}
