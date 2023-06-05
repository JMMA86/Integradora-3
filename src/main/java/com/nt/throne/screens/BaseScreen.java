package com.nt.throne.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class BaseScreen {

    protected Canvas canvas;
    protected GraphicsContext graphicsContext;

    public BaseScreen(Canvas canvas) {
        this.canvas = canvas;
        canvas.setHeight(720);
        canvas.setWidth(1280);
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public abstract void paint();

    public abstract void onMousePressed(MouseEvent event);

    public abstract void onMouseClicked(MouseEvent event);

    public abstract void onMouseMoved(MouseEvent event);
}
