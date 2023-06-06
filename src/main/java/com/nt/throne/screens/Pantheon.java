package com.nt.throne.screens;

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

import java.util.ArrayList;

public class Pantheon extends BaseScreen {
    private Hero hero = Hero.getInstance();
    private ArrayList<Structure> structures;
    private ArrayList<Bullet> bullets;
    private Image tImage;
    private final ArrayList<Image> scenario;
    private int scenarioNum;

    public Pantheon(Canvas canvas) {
        super(canvas);
        initStructures();
        tImage = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/TestBullet.png");
        bullets = new ArrayList<>();
        scenarioNum = 0;
        scenario = new ArrayList<>();
        structures = new ArrayList<>();
        initStructures();
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1.png"));
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2.png"));
        scenario.add(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3.png"));
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
        for (Structure structure : structures) {
            graphicsContext.drawImage(structure.getPicture(), structure.getPosition().getX(), structure.getPosition().getY());
        }
        hero.paint(graphicsContext);
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
