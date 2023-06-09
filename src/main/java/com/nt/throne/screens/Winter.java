package com.nt.throne.screens;

import com.nt.throne.model.ChaserEnemy;
import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.Random;

public class Winter extends Scenario {
    private boolean enemyAdded;
    public Winter(Canvas canvas, Image background) {
        super(canvas, background);
        enemyAdded = false;
    }

    @Override
    public void initElements() {
        generateGuns();
        Random random = new Random();
        int numShooterEnemies = random.nextInt(1, 3);
        int numChaserEnemies = random.nextInt(2, 5);
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

        setMovingEnemies(true);

        //Blocks: 68x135 (68x68 without shadow)
        getStructures().add(new Structure(new Point2D(375, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(443, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(511, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(579, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(647, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(715, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(783, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(375, 404), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(851, 404), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(851, 180), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(375, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(443, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(511, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(579, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(647, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(715, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(783, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(851, 472), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(375, 248), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(545, 330), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(681, 330), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(851, 248), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
    }
}
