package com.nt.throne.screens;

import com.nt.throne.model.ChaserEnemy;
import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.Random;

public class RedDesert extends Scenario {
    private boolean enemyAdded;
    public RedDesert(Canvas canvas, Image background) {
        super(canvas, background);
        enemyAdded = false;
    }

    @Override
    public void initElements() {
        generateGuns();
        Random random = new Random();
        generateGuns();
        int numShooterEnemies = random.nextInt(3, 5);
        int numChaserEnemies = random.nextInt(3, 5);
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
        getStructures().add(new Structure(new Point2D(536, 162), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(264, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(400, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(468, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(536, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(264, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(400, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(470, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(540, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(540, 468), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(540, 536), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(700, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(768, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(836, 230), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(700, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(768, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(836, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(998, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(998, 368), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
    }
}
