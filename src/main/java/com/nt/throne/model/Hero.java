package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.screens.Scenario;
import com.nt.throne.screens.SkinScreen;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class Hero extends Character {
    private final boolean[] pressedKeys;
    private static Hero instance;
    private Gun actualGun;

    public static Hero getInstance() {
        if(instance == null) {
            Image image = null;
            //Skin selector
            switch (SkinScreen.getSkinSelected()) {
                case 0 -> image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero1.png" );
                case 1 -> image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero2.png" );
                case 2 -> image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png" );
            }
            instance = new Hero(new Point2D(128,128), image);
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
        switch (event.getCode()) {
            case S -> {
                if( getState() != 3 && getState() != 4 ) setState(1);
                pressedKeys[0] = true;
            }
            case W -> {
                if( getState() != 3 && getState() != 4 ) setState(2);
                pressedKeys[1] = true;
            }
            case A -> {
                setState(3);
                pressedKeys[2] = true;
            }
            case D -> {
                setState(4);
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
        Point2D previous = getPosition();
        if(pressedKeys[0]) {
            if (getPosition().getY() >= Scenario.getLimitY()[1] || checkBlockCollision(1)) {
                setPosition(getPosition().add(0, -8));
            }
            setPosition(getPosition().add(0, 8));
        }
        if(pressedKeys[1]) {
            if (getPosition().getY() <= Scenario.getLimitY()[0] || checkBlockCollision(0)) {
                setPosition(getPosition().add(0, 8));
            }
            setPosition(getPosition().add(0, -8));
        }
        if(pressedKeys[2]) {
            if (getPosition().getX() <= Scenario.getLimitX()[0]) {
                if (getPosition().getY() > 330 && getPosition().getY() < 423 && InGameViewController.getSCREEN() > 0) {
                    InGameViewController.getScreens().get(InGameViewController.getSCREEN()).clearBullets();
                    InGameViewController.setSCREEN(InGameViewController.getSCREEN() - 1);
                    setPosition(new Point2D(1199, getPosition().getY()));
                } else {
                    setPosition(getPosition().add(8, 0));
                }
            } else if (checkBlockCollision(2)) {
                setPosition(getPosition().add(8, 0));
            }
            setPosition(getPosition().add(-8, 0));
        }
        if(pressedKeys[3]) {
            if (getPosition().getX() >= Scenario.getLimitX()[1]) {
                if (getPosition().getY() > 330 && getPosition().getY() < 423 && InGameViewController.getSCREEN() < InGameViewController.getMapsSize() - 1) {
                    InGameViewController.getScreens().get(InGameViewController.getSCREEN()).clearBullets();
                    InGameViewController.setSCREEN(InGameViewController.getSCREEN() + 1);
                    setPosition(new Point2D(81, getPosition().getY()));
                } else {
                    setPosition(getPosition().add(-8, 0));
                }
            } else if (checkBlockCollision(3)) {
                setPosition(getPosition().add(-8, 0));
            }
            setPosition(getPosition().add(8, 0));
        }
        if(previous.equals(getPosition())) setState(0);
    }

    public boolean checkBlockCollision(int movement) {
        /*
        Lets define movement:
        0 -> up
        1 -> down
        2 -> left
        3 -> right
         */
        ArrayList<Structure> blocks = InGameViewController.getScreens().get(InGameViewController.getSCREEN()).getStructures();
        for (Structure block : blocks) {
            Point2D posHero = getPosition();
            /*
            Shape coords (Block = 68x68)
            posX1 - posX2
            posY1
             */
            Point2D posX1 = new Point2D(block.getPosition().getX(), block.getPosition().getY());
            Point2D posX2 = new Point2D(block.getPosition().getX() + 68, block.getPosition().getY());
            Point2D posY1 = new Point2D(block.getPosition().getX(), block.getPosition().getY() + 68);
            switch (movement) {
                case 0 -> {
                    //From below
                    if (posHero.getX() > posX1.getX() - 28 && posHero.getX() < posX2.getX() + 23 && posHero.getY() <= posY1.getY() && posHero.getY() > posX1.getY()) return true;
                }
                case 1 -> {
                    //From above
                    if (posHero.getX() > posX1.getX() - 28 && posHero.getX() < posX2.getX() + 23 && posHero.getY() >= posX1.getY() - 70 && posHero.getY() + 10 < posY1.getY()) return true;
                }
                case 2 -> {
                    //From right
                    if (posHero.getY() > posX1.getY() - 60 && posHero.getY() + 10 < posY1.getY() && posHero.getX() <= posX2.getX() + 35 && posHero.getX() > posX1.getX()) return true;
                }
                case 3 -> {
                    //From left
                    if (posHero.getY() > posX1.getY() - 60 && posHero.getY() + 10 < posY1.getY() && posHero.getX() >= posX1.getX() - 35 && posHero.getX() < posX2.getX()) return true;
                }
            }
        }
        return false;
    }

    @Override
    public void attack() {

    }

    public int shot() {
        return getActualGun().onShot();
    }

    public Gun getActualGun() {
        return actualGun;
    }

    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
    }
}
