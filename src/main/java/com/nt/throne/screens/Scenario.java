package com.nt.throne.screens;

import com.nt.throne.model.*;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Scenario extends BaseScreen {
    private static int[] limitX;
    private static int[] limitY;
    private final Image background;
    private Hero hero = Hero.getInstance();
    private CopyOnWriteArrayList<Enemy> enemies;
    private CopyOnWriteArrayList<Structure> structures;
    private CopyOnWriteArrayList<Bullet> bullets;
    private CopyOnWriteArrayList<Gun> guns;
    private boolean areGunsGenerated;
    private Random random;
    private boolean shooting;
    private boolean movingEnemies;
    private final MediaPlayer bodyImpactSound;
    private final MediaPlayer blockImpactSound;

    public Scenario(Canvas canvas, Image background) {
        super(canvas);
        bodyImpactSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/bodyImpactSound.mp3").toURI().toString()));
        blockImpactSound = new MediaPlayer(new Media(new File(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Audio/GameSong/blockImpactSound.mp3").toURI().toString()));
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
        graphicsContext.drawImage(background,
            0, 0);
        for (Structure structure : structures) structure.paint(graphicsContext);
        hero.paint(graphicsContext);
        for (Bullet bullet : bullets) bullet.paint(graphicsContext);
        if (areGunsGenerated) for (Gun gun : guns) gun.paint(graphicsContext);
        if (hero.getActualGun() != null) hero.getActualGun().paint(graphicsContext);
        for (Enemy enemy : enemies) {
            if (enemy instanceof ShooterEnemy) {
                ((ShooterEnemy) enemy).getActualGun().paint(graphicsContext);
            }
            enemy.paint(graphicsContext);
        }
        run();
    }

    private void run() {
        gunsLogic();
        bullets.removeIf(this::bulletsLogic);
        if (movingEnemies) {
            for (Enemy enemy : enemies) {
                if (enemy instanceof ShooterEnemy shooter) {
                    shooter.setFocus(hero.getPosition());
                    shooter.moveAndShot(hero.getPrefferedArea(), getBullets());
                }
                if(enemy instanceof ChaserEnemy chaser) {
                    chaser.calculateMovement();
                }
            }
        }
    }

    private boolean bulletsLogic(Bullet bullet) {
        boolean ans = !isInBounds(bullet);
        for (Enemy enemy : enemies) {
            if (bullet.isHurting(enemy)) {
                bodyImpactSound.play();
                enemy.takeDamage(bullet);
                if (enemy.getLife() <= 0) {
                    enemies.remove(enemy);
                }
                ans = true;
            }
        }

        for (Structure structure : structures) {
            if (bullet.isHurting(structure)) {
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
                            40
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
                if (hero.getActualGun() != null) {
                    hero.getActualGun().setPosition(hero.getPosition());
                    guns.add(hero.getActualGun());
                }
                hero.setActualGun(gun);
                guns.remove(gun);
            }
        }
    }

    private Boolean checkFreePosition(int x, int y) {
        Shape temp = new Rectangle(x, y, 60, 30);
        for (Structure structure : structures) {
            if (structure.getHitBox().intersects((Bounds) temp)) {
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
        new Thread(() -> {
            makeSound();
            while (shooting) {
                shoot(event);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                hero.getActualGun().getShotSound().stop();
                hero.getActualGun().getShotSound().seek(Duration.ZERO);
            }
        }).start();
    }

    public void makeSound() {
        new Thread(() -> {
            while (shooting) {
                hero.getActualGun().getShotSound().play();
                try {
                    Thread.sleep((long) hero.getActualGun().getShotSound().getCycleDuration().toMillis());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            hero.getActualGun().getShotSound().stop();
            hero.getActualGun().getShotSound().seek(Duration.ZERO);
        }).start();
    }

    public void shoot(MouseEvent event) {
        if (hero.getActualGun() != null) {
            hero.getActualGun().getShotSound().play();
            hero.getActualGun().onShot(bullets, new Point2D(event.getX(), event.getY()));
        }
        try {
            Thread.sleep(150);
            hero.getActualGun().getShotSound().stop();
            hero.getActualGun().getShotSound().seek(Duration.ZERO);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event) {

    }

    @Override
    public void onMouseMoved(MouseEvent event) {

    }

    @Override
    public void onMouseClicked(MouseEvent event) {

    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        shooting = false;
        hero.getActualGun().getShotSound().stop();
        hero.getActualGun().getShotSound().seek(Duration.ZERO);
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        hero.onKeyPressed(event);
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
}
