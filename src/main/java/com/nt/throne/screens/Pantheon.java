package com.nt.throne.screens;

import com.nt.throne.model.*;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Pantheon extends BaseScreen{
    private Hero hero = Hero.getInstance();
    private ArrayList<Structure> structures;


    public Pantheon(Canvas canvas) {
        super(canvas);
        initStructures();

    }

    private void initStructures() {
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
