package com.nt.throne.screens;

import com.nt.throne.model.*;
import com.nt.throne.model.Bullet;
import com.nt.throne.model.Hero;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Scenario extends BaseScreen {
    private static int[] limitX;
    private static int[] limitY;
    private Hero hero = Hero.getInstance();
    private ArrayList<Enemy> enemies;
    private ArrayList<Structure> structures;
    private CopyOnWriteArrayList<Bullet> bullets; //Allows multiple iterations
    private final Image background;
    private boolean shooting;
    private MouseEvent mouseCoords;
    private long lastShootTime = 0;
    private final int SHOOT_INTERVAL = 300;

    public Scenario(Canvas canvas, Image background) {
        super(canvas);
        this.background = background;
        structures = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new CopyOnWriteArrayList<>();
        limitX = new int[2];
        limitY = new int[2];
        //Limit declaration
        limitX[0] = 80;
        limitX[1] = 1200;
        limitY[0] = 70;
        limitY[1] = 620;
        initElements();
    }

    // Initialize all the enemies and structures in the scenario
    public abstract void initElements();

    @Override
    public void paint() {
        graphicsContext.drawImage(background,
                0, 0);
        for(Structure structure : structures) structure.paint(graphicsContext);
        for (Enemy enemy : enemies) enemy.paint(graphicsContext);
        hero.paint(graphicsContext);
        for (Bullet bullet : bullets) bullet.paint(graphicsContext);
        run();
    }

    private void run() {
        bullets.removeIf(this::bulletsLogic);
    }

    private boolean bulletsLogic(Bullet bullet) {
        boolean ans = !isInBounds(bullet);
        if (enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (bullet.isHurting(enemies.get(i))) {
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

    public void clearBullets() {
        bullets.clear();
    }

    private boolean isInBounds(Element element) {
        //This functions checks if the element is in bounds
        return !(element.getPosition().getX() > canvas.getWidth() + 5)
                && !(element.getPosition().getX() < -5)
                && !(element.getPosition().getY() > canvas.getHeight() + 5)
                && !(element.getPosition().getY() < -5);
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        shooting = true;
        new Thread(() -> {
            while (shooting) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShootTime >= SHOOT_INTERVAL) {
                    shoot(mouseCoords);
                    lastShootTime = currentTime;
                }
            }
        }).start();
    }

    public void shoot(MouseEvent event) {
        bullets.add(new Bullet(hero.getPosition(), calcUnitVector(hero.getPosition(), new Point2D(event.getX(), event.getY())), 8.5, 30,
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Guns/bullet.png")
        ));
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        mouseCoords = event;
    }

    @Override
    public void onMouseMoved(MouseEvent event) {
        mouseCoords = event;
    }

    @Override
    public void onMouseClicked(MouseEvent event) {
        //shoot(event);
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        shooting = false;
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        hero.onKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        hero.onKeyReleased(event);
    }

    public Point2D calcUnitVector(Point2D origin, Point2D dest) {
        double deltaX = dest.getX() - origin.getX();
        double deltaY = dest.getY() - origin.getY();
        double magnitude = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return new Point2D(deltaX / magnitude, deltaY / magnitude);
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void setStructures(ArrayList<Structure> structures) {
        this.structures = structures;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(CopyOnWriteArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public static int[] getLimitX() {
        return limitX;
    }

    public static int[] getLimitY() {
        return limitY;
    }
}
