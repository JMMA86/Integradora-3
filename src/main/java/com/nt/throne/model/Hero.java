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
            Image image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero2.png" );
            instance = new Hero(new Point2D(0,0), image) ;
        }
        return instance;
    }

    public Hero(Point2D position, Image picture) {
        super(position, picture);
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
