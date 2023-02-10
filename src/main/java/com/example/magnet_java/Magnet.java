package com.example.magnet_java;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;

public class Magnet extends Rectangle {
    public Rectangle getsMagnet() {
        return sMagnet;
    }

    public Rectangle getnMagnet() {
        return nMagnet;
    }

    private final Rectangle sMagnet;
    private final Rectangle nMagnet;
    private final double width;
    private final double height;
    public double centerX;
    public double centerY;

    public Magnet(double centerX, double centerY, double width, double height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;

        double leftX = centerX - width / 4;
        double leftY = centerY - height / 2;
        sMagnet = new Rectangle(leftX, leftY, width / 2, height);
        sMagnet.setFill(Color.RED);

        double rightX = centerX + width / 4;
        double rightY = centerY - height / 2;

        nMagnet = new Rectangle(rightX, rightY, width / 2, height);
        nMagnet.setFill(Color.BLUE);
    }

    public void draw(GraphicsContext gc) {
        double rightX = centerX + this.width / 4;
        double leftX = centerX - this.width / 4;
        double y = centerY - this.height / 2;
        sMagnet.setX(leftX);
        sMagnet.setY(y);
        nMagnet.setX(rightX);
        nMagnet.setY(y);

        gc.save();
        gc.setFill(Color.BLUE);
        gc.fillRect(sMagnet.getX() - sMagnet.getWidth()/2, sMagnet.getY() - sMagnet.getHeight()/2, sMagnet.getWidth(), sMagnet.getHeight());
        gc.setFill(Color.RED);
        gc.fillRect(nMagnet.getX() - nMagnet.getWidth()/2, nMagnet.getY() - nMagnet.getHeight()/2, nMagnet.getWidth(), nMagnet.getHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(40));
        gc.fillText("N", nMagnet.getX() - 15, nMagnet.getY() + 15);
        gc.fillText("S", sMagnet.getX() - 15, sMagnet.getY() + 15);
        gc.restore();
    }

    public void rotate(double angle) {
        Affine affine = new Affine();
        affine.appendRotation(angle, centerX, centerY);

        sMagnet.getTransforms().clear();
        sMagnet.getTransforms().add(affine);

        nMagnet.getTransforms().clear();
        nMagnet.getTransforms().add(affine);
    }

    public double getMagneticMoment(double I) {
        return I * (nMagnet.getX() - sMagnet.getX());
    }

    public boolean contains(double x, double y) {
        double magnetX = this.centerX;
        double magnetY = this.centerY;
        return x > magnetX - width / 2 && x < magnetX + width / 2 &&
                y > magnetY - height / 2 && y < magnetY + height / 2;
    }

}
