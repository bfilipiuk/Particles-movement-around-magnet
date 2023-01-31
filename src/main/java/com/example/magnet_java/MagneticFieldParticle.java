package com.example.magnet_java;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class MagneticFieldParticle {
    private double x;
    private double y;
    private double width;
    private double height;
    private double angle;

    public MagneticFieldParticle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = Math.random() * 360;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(x, y);
        gc.rotate(angle);
        gc.setFill(Color.GREEN);
        gc.fillRect(-width / 2, -height / 2, width, height);
        gc.restore();
        angle += 0.1;
    }
}