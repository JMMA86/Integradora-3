package com.nt.throne.model;

import com.nt.throne.controller.InGameViewController;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Character extends AliveElement implements IAct {
    private Gun currentGun;
    private int currentFrame;
    private int currSprite;
    private long invulnerability;
    private boolean canGetDamage;

    public Character(Point2D position, Image picture) {
        super(position, picture);
        this.currentGun = null;
        this.currSprite = 10;
        this.currentFrame = 0;
        this.canGetDamage = true;
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    public void setCurrentGun(Gun currentGun) {
        this.currentGun = currentGun;
    }

    @Override
    public void paint(GraphicsContext context) {
        int frameWidth = 64, frameHeight = 64;
        move();
        setHitBox(new Rectangle(getPosition().getX() - 16, getPosition().getY() - 32, 32, 64));

        switch (getState()) {
            case 0 -> currentFrame = 0;
            case 1 -> currSprite = 10;
            case 2 -> currSprite = 8;
            case 3 -> currSprite = 9;
            case 4 -> currSprite = 11;
        }

        context.drawImage( getPicture(),
                currentFrame*frameWidth, currSprite*frameHeight,
                frameWidth, frameHeight,
                getPosition().getX() - frameWidth, getPosition().getY() - frameHeight,
                frameWidth*2, frameHeight*2);

        if(getState() != 0) currentFrame++;
        if(currentFrame % 9 == 0) currentFrame = 0;
    }

    public void startInvulnerabilityTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                canGetDamage = true;
            }
        };

        timer.schedule(task, invulnerability);
    }

    @Override
    public void takeDamage(Element origin) {
        if (canGetDamage) {
            if (origin instanceof Bullet) {
                setLife(getLife() - ((Bullet) origin).getDamage());
                canGetDamage = false;
                startInvulnerabilityTimer();
            }
        }
    }

    public boolean checkBlockCollision(int movement) {
        /*
        Lets define movement:
        0 -> up
        1 -> down
        2 -> left
        3 -> right
         */
        CopyOnWriteArrayList<Structure> blocks = InGameViewController.getScreens().get(InGameViewController.getSCREEN()).getStructures();

        Shape hitBox = getHitBox();

        for (Structure block : blocks) {
            /*
            Shape coords (Block = 68x68)
            posX1 - posX2
            posY1
             */
            Point2D posX1 = new Point2D(block.getPosition().getX(), block.getPosition().getY() - 30);
            Point2D posX2 = new Point2D(block.getPosition().getX() + 68, block.getPosition().getY() - 30);
            Point2D posY1 = new Point2D(block.getPosition().getX(), block.getPosition().getY() + 34);
            Point2D posY2 = new Point2D(block.getPosition().getX()+68, block.getPosition().getY()+34);

            int diff = 10;

            switch (movement) {
                case 0 -> {
                    if (( new Line(posY1.getX() + diff, posY1.getY(), posY2.getX() - diff, posY2.getY()) ).intersects(hitBox.getBoundsInParent())) {
                        return true;
                    }
                }
                case 1 -> {
                    if(( new Line(posX1.getX() + diff, posX1.getY(), posX2.getX() - diff, posX2.getY()) ).intersects(hitBox.getBoundsInParent())) {
                        return true;
                    }
                }
                case 2 -> {
                    if(( new Line(posX2.getX(), posX2.getY() + diff, posY2.getX(), posY2.getY() - diff )).intersects(hitBox.getBoundsInParent())) {
                        return true;
                    }
                }
                case 3 -> {
                    if(( new Line(posX1.getX(), posX1.getY() + diff, posY1.getX(), posY1.getY()- diff ) ).intersects(hitBox.getBoundsInParent())) {
                        return true;
                    }
                }
            }

            /*
            switch (movement) {
                case 0 -> {
                    //From below
                    if (posHero.getX() > posX1.getX() - 28 && posHero.getX() < posX2.getX() + 23 && posHero.getY() <= posY1.getY() && posHero.getY() > posX1.getY())
                        return true;
                }
                case 1 -> {
                    //From above
                    if (posHero.getX() > posX1.getX() - 28 && posHero.getX() < posX2.getX() + 23 && posHero.getY() >= posX1.getY() - 70 && posHero.getY() + 10 < posY1.getY())
                        return true;
                }
                case 2 -> {
                    //From right
                    if (posHero.getY() > posX1.getY() - 70 && posHero.getY() + 10 < posY1.getY() && posHero.getX() <= posX2.getX() + 35 && posHero.getX() > posX1.getX())
                        return true;
                }
                case 3 -> {
                    //From left
                    if (posHero.getY() > posX1.getY() - 70 && posHero.getY() + 10 < posY1.getY() && posHero.getX() >= posX1.getX() - 35 && posHero.getX() < posX2.getX())
                        return true;
                }
            }
             */
        }
        return false;
    }



    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public long getInvulnerability() {
        return invulnerability;
    }

    public void setInvulnerability(long invulnerability) {
        this.invulnerability = invulnerability;
    }

    public boolean isCanGetDamage() {
        return canGetDamage;
    }

    public void setCanGetDamage(boolean canGetDamage) {
        this.canGetDamage = canGetDamage;
    }
}
