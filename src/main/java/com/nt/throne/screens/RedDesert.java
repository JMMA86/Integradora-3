package com.nt.throne.screens;

import com.nt.throne.model.ChaserEnemy;
import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class RedDesert extends Scenario {
    public RedDesert(Canvas canvas, Image background) {
        super(canvas, background);
    }

    @Override
    public void initElements() {
        generateGuns();
        setMovingEnemies(true);

        getEnemies().add(
            new ShooterEnemy(
                new Point2D(800, 600),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/shooter.png")
            )
        );
        getEnemies().add(
            new ShooterEnemy(
                new Point2D(300, 600),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/shooter.png")
            )
        );
        getEnemies().add(
            new ChaserEnemy(
                new Point2D(500, 100),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
            )
        );
        getEnemies().add(
            new ChaserEnemy(
                new Point2D(800, 600),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
            )
        );
        getEnemies().add(
            new ChaserEnemy(
                new Point2D(300, 300),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/chaser.png")
            )
        );

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
