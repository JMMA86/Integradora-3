package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public abstract class Element {
    private Point2D position;
    private Shape hitBox;
    private Image picture;
    private int state;

    public Element(Point2D position, Shape hitBox, Image picture, int state) {
        this.position = position;
        this.hitBox = hitBox;
        this.picture = picture;
        this.state = state;
    }
}
