package com.nt.throne.screens;

import com.nt.throne.model.*;
import com.nt.throne.model.Bullet;
import com.nt.throne.model.Hero;
import com.nt.throne.model.Structure;
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
import java.util.Arrays;

public class Pantheon extends BaseScreen {
    private Hero hero = Hero.getInstance();
    private ArrayList<Enemy> enemies;
    private ArrayList<Structure> structures;
    private ArrayList<Bullet> bullets;
    private Image tImage;
    private Image tEnemy;
    private final ArrayList<Image> scenario;
    private int scenarioNum;

    public Pantheon(Canvas canvas) {
        super(canvas);
        tImage = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/TestBullet.png");
        tEnemy = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png");
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new ShooterEnemy(new Point2D(500, 500), tEnemy));
        scenarioNum = 0;
        scenario = new ArrayList<>();
        structures = new ArrayList<>();
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1.png"));
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2.png"));
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3.png"));
        initStructures();
    }

    private void initStructures() {
        //Scenarios 0 - 1 - 2
        //Blocks: 68x135
        switch (scenarioNum) {
            case 0 -> {
                structures.add(new Structure(new Point2D(300, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(368, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(436, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(504, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(250, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(318, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(386, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(454, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(680, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(680, 385), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(680, 520), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
                structures.add(new Structure(new Point2D(890, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
            }
            case 1 -> {
                structures.add(new Structure(new Point2D(300, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(375, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(450, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(525, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(300, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(375, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(450, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(525, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(680, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(680, 385), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(680, 520), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
                structures.add(new Structure(new Point2D(800, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
            }
            case 2 -> {
                structures.add(new Structure(new Point2D(400, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(470, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(540, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(400, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(470, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(540, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(700, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(770, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(700, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(770, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(900, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
                structures.add(new Structure(new Point2D(970, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
            }
        }
    }

    @Override
    public void paint() {
        graphicsContext.drawImage(scenario.get(scenarioNum), 0, 0);
        for (Structure structure : structures) structure.paint(graphicsContext);
        for (Enemy enemy : enemies) enemy.paint(graphicsContext);
        hero.paint(graphicsContext);
        for (Bullet bullet : bullets) bullet.paint(graphicsContext);

        bullets.removeIf(this::bulletsLogic);

        // run();
    }

    /*
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


     */


    public boolean bulletsLogic(Bullet bullet) {
        boolean ans = !isInBounds(bullet);

        if (enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (bullet.isHurting(enemies.get(i))) {
                    System.out.println("auch");
                    enemies.get(i).takeDamage(bullet);
                    //Insecure execution, looking to refactor this
                    if (enemies.get(i).getLife() <= 0) {
                        enemies.remove(enemies.get(i));
                    }
                    ans = true;
                }
            }
        }

        return ans;
    }

    public boolean isInBounds(Element element) {
        //This functions checks if the element is in bounds
        return !(element.getPosition().getX() > canvas.getWidth() + 5)
                && !(element.getPosition().getX() < -5)
                && !(element.getPosition().getY() > canvas.getHeight() + 5)
                && !(element.getPosition().getY() < -5);
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
