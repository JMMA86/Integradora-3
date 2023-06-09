package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.screens.Scenario;
import com.nt.throne.screens.SkinScreen;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

public class Hero extends Character {
    private static Hero instance;
    private final boolean[] pressedKeys;
    private Gun actualGun;
    private boolean canShot;
    private final Circle preferredArea;

    private Hero(Point2D position, Image picture) {
        super(position, picture);
        actualGun = null;
        pressedKeys = new boolean[]{false, false, false, false};
        preferredArea = new Circle(getPosition().getX(), getPosition().getY(), 300);
        setInvulnerability(500);
    }

    public static Hero getInstance() {
        if (instance == null) {
            Image image = null;
            //Skin selector
            switch (SkinScreen.getSkinSelected()) {
                case 0 ->
                    image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero1.png");
                case 1 ->
                    image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero2.png");
                case 2 ->
                    image = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png");
            }
            instance = new Hero(new Point2D(128, 128), image);
        }
        return instance;
    }

    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case S -> {
                if (getState() != 3 && getState() != 4) setState(1);
                pressedKeys[0] = true;
            }
            case W -> {
                if (getState() != 3 && getState() != 4) setState(2);
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
        if (pressedKeys[0]) {
            if (getPosition().getY() >= Scenario.getLimitY()[1] || checkBlockCollision(1)) {
                setPosition(getPosition().add(0, -8));
            }
            setPosition(getPosition().add(0, 8));
        }
        if (pressedKeys[1]) {
            if (getPosition().getY() <= Scenario.getLimitY()[0] || checkBlockCollision(0)) {
                setPosition(getPosition().add(0, 8));
            }
            setPosition(getPosition().add(0, -8));
        }
        if (pressedKeys[2]) {
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
        if (pressedKeys[3]) {
            if (getPosition().getX() >= Scenario.getLimitX()[1]) {
                if (getPosition().getY() > 330 && getPosition().getY() < 423 && InGameViewController.getScreens().get(InGameViewController.getSCREEN()).isLevelPassed()) {
                    if (InGameViewController.getSCREEN() < InGameViewController.getMapsSize() - 1) {
                        InGameViewController.getScreens().get(InGameViewController.getSCREEN()).clearBullets();
                        InGameViewController.setSCREEN(InGameViewController.getSCREEN() + 1);
                        setPosition(new Point2D(81, getPosition().getY()));
                    } else {
                        InGameViewController.getScreens().get(InGameViewController.getSCREEN()).setEndGameWin(true);
                    }
                } else {
                    setPosition(getPosition().add(-8, 0));
                }
            } else if (checkBlockCollision(3)) {
                setPosition(getPosition().add(-8, 0));
            }
            setPosition(getPosition().add(8, 0));
        }
        if (previous.equals(getPosition())) setState(0);
        preferredArea.setCenterX(getPosition().getX());
        preferredArea.setCenterY(getPosition().getY());
        if (actualGun != null) actualGun.setPosition(this.getPosition());
    }

    @Override
    public void attack(AliveElement target) {

    }

    public Gun getActualGun() {
        return actualGun;
    }

    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
        actualGun.setPosition( new Point2D(getPosition().getX() - actualGun.getPicture().getWidth()/2, getPosition().getY()));
    }

    public boolean isShooting() {
        return canShot;
    }

    public void setCanShot(boolean canShot) {
        this.canShot = canShot;
    }

    public Circle getPreferredArea() {
        return preferredArea;
    }
}
