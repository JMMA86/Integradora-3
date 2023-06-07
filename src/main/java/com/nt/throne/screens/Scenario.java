package com.nt.throne.screens;

import com.nt.throne.model.*;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Scenario extends BaseScreen {
    private static int[] limitX;
    private static int[] limitY;
    private Hero hero = Hero.getInstance();
    private ArrayList<Enemy> enemies;
    private ArrayList<Structure> structures;
    private ArrayList<Bullet> bullets;
    private ArrayList<Gun> guns;
    private boolean areGunsGenerated;
    private Random random;
    private final Image background;

    public Scenario(Canvas canvas, Image background) {
        super(canvas);
        this.background = background;
        structures = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        guns = new ArrayList<>();
        random = new Random();

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
        for (Structure structure : structures) structure.paint(graphicsContext);
        for (Enemy enemy : enemies) enemy.paint(graphicsContext);
        hero.paint(graphicsContext);
        for (Bullet bullet : bullets) bullet.paint(graphicsContext);
        if (areGunsGenerated) for (Gun gun : guns) gun.paint(graphicsContext);
        run();
    }

    private void run() {
        gunsLogic();
        bullets.removeIf(this::bulletsLogic);
    }

    /**
     * Performs the bullet collision with elements, returns true if
     * the bullet must be removed from the bullets list
     *
     * @param bullet The bullet to check
     * @return True if the bullet hit an element
     */
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

    public void generateGuns() {
        int totalGuns = 0;
        boolean machineGun = true;

        while (totalGuns < 2) {
            //326 * 121 MG
            //284 * 47 SG
            int x = random.nextInt(50, (int) (canvas.getWidth() - 50));
            int y = random.nextInt(50, (int) (canvas.getHeight() - 50));
            if (checkFreePosition(x, y)) {
                if (machineGun) {
                    getGuns().add(
                        new MachineGun(
                            new Point2D(x, y),
                            new Image(
                                System.getProperty("user.dir") +
                                    "/src/main/resources/com/nt/throne/Guns/minigun.png"
                            ),
                            60
                        )
                    );
                    machineGun = false;
                } else {
                    getGuns().add(
                        new ShotGun(
                            new Point2D(x, y),
                            new Image(
                                System.getProperty("user.dir") +
                                    "/src/main/resources/com/nt/throne/Guns/shotgun.png"
                            ),
                            10
                        )
                    );
                    machineGun = true;
                }
                totalGuns += 1;
            }
        }

        areGunsGenerated = true;
    }

    public void gunsLogic() {
        for (Gun gun : guns) {
            if (hero.isColliding(gun)) {
                hero.setActualGun(gun);
                //Point2D temp = new Point2D(random.nextInt(limitX[0], limitX[1] - 50), random.nextInt(limitY[0], limitY[1]));
                //gun.setPosition(temp);
            }
        }
    }

    private Boolean checkFreePosition(int x, int y) {
        Shape temp = new Rectangle(x, y, 30, 30);
        for (Structure structure : structures) {
            if (structure.getHitBox().intersects((Bounds) temp)) {
                return false;
            }
        }

        return true;
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
        if (hero.getActualGun() != null) {
            Timer timer = new Timer();
            int numShots = hero.shot();
            int shotDelay = 20;
            for (int i = 0; i < numShots; i++) {
                final int shotIndex = i;
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        bullets.add(
                            new Bullet(
                                hero.getPosition(),
                                calcUnitVector(
                                    hero.getPosition(),
                                    new Point2D(event.getX(), event.getY())
                                ),
                                8.5,
                                30,
                                new Image(
                                    System.getProperty("user.dir") +
                                        "/src/main/resources/com/nt/throne/Guns/bullet.png"
                                )
                            )
                        );

                        if (shotIndex == numShots - 1) {
                            timer.cancel();
                            timer.purge();
                        }
                    }
                };

                timer.schedule(task, (long) i * shotDelay);
            }
        }
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Gun> getGuns() {
        return guns;
    }

    public void setGuns(ArrayList<Gun> disposableGuns) {
        this.guns = disposableGuns;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static int[] getLimitX() {
        return limitX;
    }

    public static int[] getLimitY() {
        return limitY;
    }
}
