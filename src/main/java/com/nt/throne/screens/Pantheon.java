package com.nt.throne.screens;

import com.nt.throne.model.Bullet;
import com.nt.throne.model.Hero;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Pantheon extends BaseScreen {
    private Hero hero = Hero.getInstance();
    private ArrayList<Structure> structures;
    private ArrayList<Bullet> bullets;
    private Image tImage;

    public Pantheon(Canvas canvas) {
        super(canvas);
        initStructures();
        tImage = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/TestBullet.png");
        bullets = new ArrayList<>();
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
    }

    public void run() {
        if (bullets.size() > 0) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).move();
                if (bullets.get(i).getPosition().getX() > canvas.getWidth() || bullets.get(i).getPosition().getY() < 0
                || bullets.get(i).getPosition().getY() > canvas.getHeight() || bullets.get(i).getPosition().getY() < 0) {
                    bullets.remove(bullets.get(i));
                }
            }
        }
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
        Point2D temp = calcUnitVector(hero.getPosition(), new Point2D(event.getX(), event.getY()));
        bullets.add( new Bullet(10, hero.getPosition(), temp, tImage));
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
