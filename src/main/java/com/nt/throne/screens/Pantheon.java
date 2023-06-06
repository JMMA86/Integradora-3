package com.nt.throne.screens;

import com.nt.throne.model.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.lang.Character;
import java.util.ArrayList;

public class Pantheon extends BaseScreen {
    private Hero hero = Hero.getInstance();
    private ArrayList<Enemy> enemies;
    private ArrayList<Structure> structures;
    private ArrayList<Bullet> bullets;
    private Image tImage;
    private Image tEnemy;

    public Pantheon(Canvas canvas) {
        super(canvas);
        initStructures();
        tImage = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/TestBullet.png");
        tEnemy = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/TestEnemy.png");
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new ShooterEnemy(new Point2D(500, 500), tEnemy));
    }

    private void initStructures() {
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        hero.paint(graphicsContext);
        if (bullets.size() > 0) {
            for (Bullet bullet : bullets) {
                bullet.paint(graphicsContext);
            }
        }

        if (enemies.size() > 0) {
            for (Enemy enemy : enemies) {
                enemy.paint(graphicsContext);
            }
        }
    }

    public void run() {
        //The function that provides the main thread the logic
        bulletsMovement();
    }

    public void bulletsMovement() {
        boolean remove = false;

        if (bullets.size() > 0) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).move();

                //Logic to hurt
                System.out.println(bulletsLogic(bullets.get(i)));
                remove = bulletsLogic(bullets.get(i)) || !isInBounds(bullets.get(i));
                System.out.println(remove);

                if (remove) {
                    bullets.remove(bullets.get(i));
                }
            }
        }
    }

    public boolean bulletsLogic(Bullet bullet) {
        //This function hurts the elements
        //if (bullet.isHurting(hero)) {
        //    return false;
        //} else
        if (enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (bullet.isHurting(enemies.get(i))) {
                    enemies.get(i).takeDamage(bullet);
                    //Insecure execution, looking to refactor this
                    if (enemies.get(i).getLife() <= 0) {
                        enemies.remove(enemies.get(i));
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isInBounds(Element element) {
        //This functions checks if the element is in bounds
        return !(element.getPosition().getX() > canvas.getWidth() + 5) && !(element.getPosition().getY() < -5)
                && !(element.getPosition().getY() > canvas.getHeight() + 5) && !(element.getPosition().getY() < -5);
    }

    @Override
    public void onMousePressed(MouseEvent event) {

    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        hero.onKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        hero.onKeyReleased(event);
    }


    @Override
    public void onMouseClicked(MouseEvent event) {
        bullets.add(new Bullet(hero.getPosition(), calcUnitVector(hero.getPosition(), new Point2D(event.getX(), event.getY())), 8.5, 10, tImage));
    }

    @Override
    public void onMouseMoved(MouseEvent event) {

    }

    public Point2D calcUnitVector(Point2D origin, Point2D dest) {
        double deltaX = dest.getX() - origin.getX();
        double deltaY = dest.getY() - origin.getY();
        double magnitude = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return new Point2D(deltaX / magnitude, deltaY / magnitude);
    }
}
