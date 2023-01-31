package com.example.magnet_java;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private int particlesAmount = 200;
    private double canvasHeight = 720;
    private double canvasWidth = 1080;
    private Magnet magnet;
    private MagneticFieldParticle[] particles;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        magnet = new Magnet(500, 360, 200, 100);
        particles = new MagneticFieldParticle[particlesAmount];
        for (int i = 0; i < particlesAmount; i++) {
            double y = Math.random() * canvasHeight;
            double x = Math.random() * canvasWidth;

            do {
                y = Math.random() * canvasHeight;
                x = Math.random() * canvasWidth;

                while (x > 430 & x < 650 & y > 250 & y < 470) {
                    y = Math.random() * canvasHeight;
                    x = Math.random() * canvasWidth;
                }
            } while (magnet.contains(x, y));
            particles[i] = new MagneticFieldParticle(x, y, 20, 2);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                magnet.rotate(0.1);
                magnet.draw(gc);
                for (MagneticFieldParticle particle : particles) {
                    particle.draw(gc);
                }
            }
        }.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

