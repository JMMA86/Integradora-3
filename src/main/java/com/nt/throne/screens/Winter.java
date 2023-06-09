package com.nt.throne.screens;

import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Winter extends Scenario {
    public Winter(Canvas canvas, Image background) {
        super(canvas, background);
    }

    @Override
    public void initElements() {
        generateGuns();
        /*
        getEnemies().add(new ShooterEnemy(
            new Point2D(500, 500),
            new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png")));

         */

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
