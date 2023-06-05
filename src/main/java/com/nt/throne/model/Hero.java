package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Hero extends Character {

    private static Hero instance;

    public static Hero getInstance() {
        if(instance == null) {
            instance = new Hero(new Point2D(0,0), FileManager.getInstance().loadSprites("character001") );
        }
        return instance;
    }

    public Hero(Point2D position, ArrayList<ArrayList<Image>> sprites) {
        super(position, sprites);
    }

    @Override
    public void takeDamage() {

    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
