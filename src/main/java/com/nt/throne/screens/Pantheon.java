package com.nt.throne.screens;

import com.nt.throne.model.ChaserEnemy;
import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Pantheon extends Scenario {
    public Pantheon(Canvas canvas, Image background) {
        super(canvas, background);
    }

    @Override
    public void initElements() {
        generateGuns();

        getEnemies().add(
            new ShooterEnemy(
                new Point2D(500, 600),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/shooter.png")
            )
        );
        getEnemies().add(
            new ChaserEnemy(
                new Point2D(800, 500),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
            )
        );
        getEnemies().add(
                new ChaserEnemy(
                        new Point2D(400, 500),
                        new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
                )
        );

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
