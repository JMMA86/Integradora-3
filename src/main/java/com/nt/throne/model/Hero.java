package com.nt.throne.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Hero extends Character {

    private boolean[] pressedKeys;

    private static Hero instance;

    public static Hero getInstance() {
        if(instance == null) {
            Image image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero2.png" );
            instance = new Hero(new Point2D(0,0), image);
        }
        return instance;
    }

    private Hero(Point2D position, Image picture) {
        super(position, picture);
        pressedKeys = new boolean[]{false, false, false, false};
    }

    @Override
    public void takeDamage(Element origin) {

    }

    public void onKeyPressed(KeyEvent event) {
        System.out.println(event.getCharacter());
        switch (event.getCode()) {
            case S -> {
                setState(0);
                pressedKeys[0] = true;
            }
            case W -> {
                setState(1);
                pressedKeys[1] = true;
            }
            case A -> {
                setState(2);
                pressedKeys[2] = true;
            }
            case D -> {
                setState(3);
                pressedKeys[3] = true;
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case S -> pressedKeys[0] = false;
            case W -> pressedKeys[1] = false;
            case A -> pressedKeys[2] = false;
            case D -> pressedKeys[3] = false;
        }
    }

    @Override
    public void move() {
        if(pressedKeys[0]) setPosition(getPosition().add(0, -1));
        if(pressedKeys[1]) setPosition(getPosition().add(0, 1));
        if(pressedKeys[2]) setPosition(getPosition().add(-1, 0));
        if(pressedKeys[3]) setPosition(getPosition().add(1, 0));
    }

    @Override
    public void attack() {

    }
}
