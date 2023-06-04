package com.nt.throne.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MenuScreen extends BaseScreen {
    public MenuScreen(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void paint() {
        Text text1 = new Text("HOLAAAAA");
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(100, 50, 200, 120);
    }

    @Override
    public void onMousePressed(MouseEvent event) {

    }
}
