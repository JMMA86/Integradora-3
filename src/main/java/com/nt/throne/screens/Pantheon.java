package com.nt.throne.screens;

import com.nt.throne.model.ChaserEnemy;
import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pantheon extends Scenario {
    private boolean enemyAdded;
    public Pantheon(Canvas canvas, Image background) {
        super(canvas, background);
        this.enemyAdded = false;
    }

    @Override
    public void initElements() {
        Random random = new Random();
        generateGuns();
        int numShooterEnemies = random.nextInt(1, 3);
        int numChaserEnemies = random.nextInt(1, 3);
        int axisX = random.nextInt(0, 1280);
        int axisY = random.nextInt(0, 720);
        for (int i = 0; i < numShooterEnemies; i++) {
            while (!enemyAdded) {
                ShooterEnemy shooterEnemy = new ShooterEnemy(
                        new Point2D(axisX, axisY),
                        new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/shooter.png")
                );
                if (checkFreePosition(axisX, axisY, shooterEnemy)) {
                    getEnemies().add(shooterEnemy);
                    enemyAdded = true;
                }
                axisX = random.nextInt(0, 1280);
                axisY = random.nextInt(0, 720);
            }
            enemyAdded = false;
        }
        for (int i = 0; i < numChaserEnemies; i++) {
            while (!enemyAdded) {
                ChaserEnemy chaserEnemy = new ChaserEnemy(
                        new Point2D(axisX, axisY),
                        new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
                );
                if (checkFreePosition(axisX, axisY, chaserEnemy)) {
                    getEnemies().add(chaserEnemy);
                    enemyAdded = true;
                }
                axisX = random.nextInt(0, 1280);
                axisY = random.nextInt(0, 720);
            }
            enemyAdded = false;
        }

        //Blocks: 68x135 (68x68 without shadow)
        getStructures().add(new Structure(new Point2D(300, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(368, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(436, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(504, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(250, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(318, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(386, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(454, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(680, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(680, 385), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(680, 520), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
        getStructures().add(new Structure(new Point2D(890, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1-block.png")));
    }
}
