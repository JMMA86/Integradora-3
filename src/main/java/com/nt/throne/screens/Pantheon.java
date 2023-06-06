package com.nt.throne.screens;

import com.nt.throne.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Pantheon extends BaseScreen{
    private Image scenario;
    private Hero hero = Hero.getInstance();
    private ArrayList<Structure> structures;


    public Pantheon(Canvas canvas) {
        super(canvas);
        initStructures();
        scenario = new Image(System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/Scenario/scenario-1.png");
    }

    private void initStructures() {
    }

    @Override
    public void paint() {
        graphicsContext.drawImage(scenario, 0, 0);
        hero.paint(graphicsContext);
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

    }

    @Override
    public void onMouseMoved(MouseEvent event) {

    }
}
