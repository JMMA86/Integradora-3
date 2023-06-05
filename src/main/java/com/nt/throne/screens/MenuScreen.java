package com.nt.throne.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MenuScreen extends BaseScreen {
    public MenuScreen(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,1280,720);
    }

    @Override
    public void onMousePressed(MouseEvent event) {

    }

    @Override
    public Canvas getCanvas() {
        return super.getCanvas();
    }

    @Override
    public GraphicsContext getGraphicsContext() {
        return super.getGraphicsContext();
    }
}
