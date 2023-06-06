package com.nt.throne.screens;

import com.nt.throne.model.ShooterEnemy;
import com.nt.throne.model.Structure;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Pantheon extends Scenario {
    public Pantheon(Canvas canvas, Image background) {
        super(canvas, background);
    }

    @Override
    public void initElements() {
        getEnemies().add(new ShooterEnemy(
                new Point2D(500, 500),
                new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/hero3.png")));

        //Blocks: 68x135
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

        /* scenario 2
        getStructures().add(new Structure(new Point2D(300, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(375, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(450, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(525, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(300, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(375, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(450, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(525, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(680, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(680, 385), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(680, 520), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
        getStructures().add(new Structure(new Point2D(800, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-2-block.png")));
         */

        /* scenario 3
        getStructures().add(new Structure(new Point2D(400, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(470, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(540, 200), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(400, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(470, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(540, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(700, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(770, 250), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(700, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(770, 400), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(900, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
        getStructures().add(new Structure(new Point2D(970, 300), new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-3-block.png")));
         */
    }

    @Override
    public void onMouseClicked(MouseEvent event) {
        System.out.println(event.getX());
        System.out.println(event.getY());
    }
}
