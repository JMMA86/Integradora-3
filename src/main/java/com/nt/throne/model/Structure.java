package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Structure extends AliveElement {
    public Structure(Point2D position, ArrayList<ArrayList<Image>> sprites) {
        super(position, sprites);
    }

    @Override
    public void takeDamage() {

    }
}
