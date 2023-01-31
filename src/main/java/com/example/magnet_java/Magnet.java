package com.example.magnet_java;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;

public class Magnet {
    private Rectangle leftRectangle;
    private Rectangle rightRectangle;
    private int width;
    private int height;
    private int centerX;
    private int centerY;

    public Magnet(int centerX, int centerY, int width, int height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;

        int leftX = centerX - width / 4;
        int leftY = centerY - height / 2;
        leftRectangle = new Rectangle(leftX, leftY, width / 2, height);
        leftRectangle.setFill(Color.RED);

        int rightX = centerX + width / 4;
        int rightY = centerY - height / 2;
        rightRectangle = new Rectangle(rightX, rightY, width / 2, height);
        rightRectangle.setFill(Color.BLUE);
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(Color.BLUE);
        gc.fillRect(leftRectangle.getX(), leftRectangle.getY(), leftRectangle.getWidth(), leftRectangle.getHeight());
        gc.setFill(Color.RED);
        gc.fillRect(rightRectangle.getX(), rightRectangle.getY(), rightRectangle.getWidth(), rightRectangle.getHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(40));
        gc.fillText("N", rightRectangle.getX() + rightRectangle.getWidth() / 2 - 15, rightRectangle.getY() + rightRectangle.getHeight()/2 + 15);
        gc.fillText("S", leftRectangle.getX() + leftRectangle.getWidth() / 2 - 15, leftRectangle.getY() + leftRectangle.getHeight()/2 + 15);
        gc.restore();
    }

    public void rotate(double angle) {
        Affine affine = new Affine();
        affine.appendRotation(angle, centerX, centerY);

        leftRectangle.getTransforms().clear();
        leftRectangle.getTransforms().add(affine);

        rightRectangle.getTransforms().clear();
        rightRectangle.getTransforms().add(affine);
    }

    public boolean contains(double x, double y) {
        double magnetX = this.centerX;
        double magnetY = this.centerY;
        return x > magnetX - width / 2 && x < magnetX + width / 2 &&
                y > magnetY - height / 2 && y < magnetY + height / 2;
    }
}
