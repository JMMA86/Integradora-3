package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    public Enemy(Point2D position, Image picture) {
        super(position, picture);
    }
}
