package com.nt.throne.screens;

import com.nt.throne.controller.InGameViewController;
import com.nt.throne.model.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import java.awt.MouseInfo;
import javafx.util.Duration;
import java.io.File;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Scenario extends BaseScreen {
    private static int[] limitX;
    private static int[] limitY;
    private final Image background;
    private final MediaPlayer bodyImpactSound;
    private final MediaPlayer blockImpactSound;
    private final ImageView aim;
    private final Image closedDoor;
    private final Image openedDoor;
    private Hero hero = Hero.getInstance();
    private CopyOnWriteArrayList<Enemy> enemies;
    private CopyOnWriteArrayList<Structure> structures;
    private CopyOnWriteArrayList<Bullet> bullets;
    private CopyOnWriteArrayList<Gun> guns;
    private boolean areGunsGenerated;
    private Random random;
    private boolean shooting;
    private boolean movingEnemies;
    private Point2D mouseCoords;
    private boolean recharging;
    private boolean levelPassed;
    private static boolean endGameWin;
    private static boolean endGameLose;

    public Scenario(Canvas canvas, Image background) {
        super(canvas);
        bodyImpactSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/bodyImpactSound.mp3").toURI().toString()));
        blockImpactSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/blockImpactSound.mp3").toURI().toString()));
        closedDoor = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/closedDoor.png");
        openedDoor = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/openedDoor.png");
        levelPassed = false;
        endGameWin = false;
        endGameLose = false;
        mouseCoords = new Point2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        aim = new ImageView(new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Guns/aim.png"));
        recharging = false;
        this.background = background;
        structures = new CopyOnWriteArrayList<>();
        enemies = new CopyOnWriteArrayList<>();
        bullets = new CopyOnWriteArrayList<>();
        guns = new CopyOnWriteArrayList<>();
        random = new Random();
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

    public static int[] getLimitX() {
        return limitX;
    }

    public static int[] getLimitY() {
        return limitY;
    }

    // Initialize all the enemies and structures in the scenario
    public abstract void initElements();

    @Override
    public void paint() {
        graphicsContext.drawImage(background, 0, 0);
        for (Structure structure : structures) structure.paint(graphicsContext);
        if (enemies.isEmpty()) {
            levelPassed = true;
            graphicsContext.drawImage(openedDoor, 1200, 320, openedDoor.getWidth() * 0.7, openedDoor.getHeight() * 0.8);
        } else {
            levelPassed = false;
            graphicsContext.drawImage(closedDoor, 1200, 320, openedDoor.getWidth() * 0.7, openedDoor.getHeight() * 0.8);
        }
        hero.paint(graphicsContext);
        for (Bullet bullet : bullets) bullet.paint(graphicsContext);
        if (areGunsGenerated) for (Gun gun : guns) gun.paint(graphicsContext);
        if (hero.getActualGun() != null) {
            Point2D gunCoords = hero.getActualGun().getPosition();
            Image gun = hero.getActualGun().getPicture();
            graphicsContext.drawImage(gun, 200 - gun.getWidth() / 2, 133 - gun.getHeight() / 2, gun.getWidth() / 1.5, gun.getHeight() / 1.5);
            //Calculate angle
            double angle = Math.atan2(mouseCoords.getY() - gunCoords.getY(), mouseCoords.getX() - gunCoords.getX());
            discardPictureWidth(gunCoords, angle, hero.getActualGun());
        }
        for (Enemy enemy : enemies) {
            if (enemy instanceof ShooterEnemy shooter) {
                //This is what I'm changing
                Point2D gunCoords = shooter.getPosition();
                //Calculate angle
                double angle = Math.atan2(hero.getPosition().getY() - gunCoords.getY(), hero.getPosition().getX() - gunCoords.getX());
                discardPictureWidth(gunCoords, angle, shooter.getActualGun());
            }
            enemy.paint(graphicsContext);
        }
        if (Hero.getInstance().getActualGun() != null) {
            graphicsContext.drawImage(aim.getImage(), 0, 0, 512, 512, mouseCoords.getX() - 40, mouseCoords.getY() - 40, 80, 80);
        }
        if (hero.getLife() <= 0) {
            endGameLose = true;
        }
        if (endGameWin) {
            InGameViewController.setWinScreen();
        }
        if (endGameLose) {
            InGameViewController.setLoseScreen();
        }
        run();
    }

    private void discardPictureWidth(Point2D position, double angle, Gun actualGun) {
        Point2D offset;
        //System.out.println("Im: " + actualGun.getPicture().getWidth());
        double realGunSize = actualGun.getPicture().getWidth() / 5;
        if (Math.abs(angle) < Math.PI / 2) {
            offset = new Point2D(position.getX() + realGunSize, position.getY() + 7);
        } else {
            offset = new Point2D(position.getX() - realGunSize, position.getY() + 7);
        }
        //System.out.println(offset);
        actualGun.setEnd(translatePoint(angle, position, offset));
        actualGun.paint(graphicsContext, angle);
    }

    private void run() {
        // gunsLogic();
        bullets.removeIf(this::bulletsLogic);
        if (movingEnemies) {
            for (Enemy enemy : enemies) {
                enemy.calculateMovement();
                if (enemy instanceof ShooterEnemy shooter) {

                    shooter.moveAndShot(hero.getPreferredArea(), getBullets());
                }
                if (enemy instanceof ChaserEnemy chaser) {
                    chaser.calculateMovement();

                    /*
                    if (chaser.isColliding(hero)) {
                        hero.takeDamage(chaser);
                    }

                     */
                }
            }
        }
    }

    private boolean bulletsLogic(Bullet bullet) {
        boolean ans = !isInBounds(bullet);

        if (bullet.isColliding(hero)) {
            hero.takeDamage(bullet);
            ans = true;
        }

        for (Enemy enemy : enemies) {
            double previousLife = enemy.getLife();
            if (bullet.isHurting(enemy)) {
                bodyImpactSound.setVolume(0.5);
                bodyImpactSound.stop();
                bodyImpactSound.seek(Duration.ZERO);
                bodyImpactSound.play();
                enemy.takeDamage(bullet);
                if(previousLife == enemy.getLife()) continue;
                if (enemy.getLife() <= 0) {
                    enemies.remove(enemy);
                }
                ans = true;
            }
        }

        for (Structure structure : structures) {
            if (bullet.isHurting(structure)) {
                blockImpactSound.setVolume(0.3);
                blockImpactSound.stop();
                blockImpactSound.seek(Duration.ZERO);
                blockImpactSound.play();
                structure.takeDamage(bullet);
                if (structure.getLife() <= 0) {
                    structures.remove(structure);
                }
                ans = true;
            }
        }

        return ans;
    }

    public void generateGuns() {
        int totalGuns = 0;
        boolean machineGun = true;
        areGunsGenerated = false;
        while (totalGuns < 2) {
            //326 * 121 MG
            //284 * 47 SG
            int x = random.nextInt(limitX[0] + 50, limitX[1] - 50);
            int y = random.nextInt(limitY[0] + 50, limitY[1] - 50);
            if (machineGun && checkFreePosition(x, y, 326, 121)) {
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
                totalGuns++;
                machineGun = false;
            } else if (!machineGun && checkFreePosition(x, y, 284, 47)) {
                getGuns().add(
                    new ShotGun(
                        new Point2D(x, y),
                        new Image(
                            System.getProperty("user.dir") +
                                "/src/main/resources/com/nt/throne/Guns/shotgun.png"
                        ),
                        40
                    )
                );
                totalGuns++;
                machineGun = true;
            }
        }
        areGunsGenerated = true;
    }

    public void gunsLogic() {
        boolean collides = false;
        for (Gun gun : guns) {
            if (hero.isColliding(gun)) {
                if (hero.getActualGun() != null) {
                    hero.getActualGun().setPosition(gun.getPosition());
                    guns.add(hero.getActualGun());
                }
                hero.setActualGun(gun);
                guns.remove(gun);
                collides = true;
            }
        }
        if(!collides && hero.getActualGun() != null) {
            hero.getActualGun().setPosition(hero.getPosition());
            guns.add(hero.getActualGun());
            hero.setActualGun(null);
        }
    }

    private Boolean checkFreePosition(int x, int y, double width, double height) {
        Rectangle temp = new Rectangle(x, y, width, height);
        for (Structure structure : structures) {
            if (structure.getHitBox().intersects(temp.getBoundsInParent())) {
                return false;
            }
        }

        return true;
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
        if (hero.getActualGun() != null) {
            new Thread(() -> {
                while (shooting) {
                    hero.getActualGun().getShotSound().stop();
                    hero.getActualGun().getShotSound().seek(Duration.ZERO);
                    if (hero.getActualGun().getAmmo() > 0) {
                        hero.getActualGun().getShotSound().play();
                    }
                    if (!recharging) {
                        shoot(mouseCoords);
                    }
                    if (hero.getActualGun().getAmmo() > 0) {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            recharging = true;
                            Thread.sleep(hero.getActualGun().getRechargeTime() + 10);
                            recharging = false;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }
    }

    public void shoot(Point2D event) {
        if (hero.getActualGun() != null) {
            hero.getActualGun().onShot(bullets, new Point2D(event.getX(), event.getY()));
            if (hero.getActualGun().getAmmo() == 0) {
                hero.getActualGun().onShot(bullets, new Point2D(event.getX(), event.getY()));
            }
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Point2D translatePoint(double angle, Point2D origin, Point2D dest) {
        double x1 = origin.getX(), y1 = origin.getY();
        double x2 = dest.getX(), y2 = dest.getY();

        double longitude = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        //double toRadians = Math.toRadians(angle);

        double newX = x1 + longitude * Math.cos(angle);
        double newY = y1 + longitude * Math.sin(angle);

        return new Point2D(newX, newY);
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        mouseCoords = new Point2D(event.getX(), event.getY());

    }

    @Override
    public void onMouseMoved(MouseEvent event) {
        mouseCoords = new Point2D(event.getX(), event.getY());
    }

    @Override
    public void onMouseClicked(MouseEvent event) {
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        shooting = false;
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SHIFT) {
            gunsLogic();
        } else {
            hero.onKeyPressed(event);
        }

    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        hero.onKeyReleased(event);
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(CopyOnWriteArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public CopyOnWriteArrayList<Structure> getStructures() {
        return structures;
    }

    public void setStructures(CopyOnWriteArrayList<Structure> structures) {
        this.structures = structures;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(CopyOnWriteArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public CopyOnWriteArrayList<Gun> getGuns() {
        return guns;
    }

    public void setGuns(CopyOnWriteArrayList<Gun> disposableGuns) {
        this.guns = disposableGuns;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public boolean isMovingEnemies() {
        return movingEnemies;
    }

    public void setMovingEnemies(boolean movingEnemies) {
        this.movingEnemies = movingEnemies;
    }

    public boolean isLevelPassed() {
        return levelPassed;
    }

    public void setEndGameWin(boolean endGame) {
        this.endGameWin = endGame;
    }

    public void setEndGameLose(boolean endGameLose) {
        this.endGameLose = endGameLose;
    }
}
