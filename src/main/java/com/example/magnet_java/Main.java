package com.example.magnet_java;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.example.magnet_java.MagneticFieldLogic.*;

public class Main extends Application {
    private int particlesAmount = 10000;
    private double canvasHeight = 720;
    private double canvasWidth = 1080;
    private Magnet magnet;
    private MagneticFieldParticle[] particles;

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        magnet = new Magnet(540, 360, 200, 100);

        magnet.setOnMouseDragged(e -> {
            System.out.println("aaa");
            if (magnet.contains(e.getX(), e.getY())) {
                magnet.setX(e.getX() - 50 / 2);
                magnet.centerX = magnet.getX();
                magnet.setY(e.getY() - 60 / 2);
                magnet.centerY = magnet.getY();
            }
        });

        root.getChildren().add(magnet);
        canvas.setOnMouseDragged(canvasOnMouseDraggedEventHandler);

        particles = new MagneticFieldParticle[particlesAmount];
        for (int i = 0; i < particlesAmount; i++) {
            double y = Math.random() * canvasHeight;
            double x = Math.random() * canvasWidth;
            particles[i] = new MagneticFieldParticle(x, y, 20, 2);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for( MagneticFieldParticle p : particles) {
                    calculateParticle(p, magnet);
                }
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (MagneticFieldParticle particle : particles) {
                    particle.draw(gc);
                }
                magnet.draw(gc);
            }
        }.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }

    EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((Canvas)(mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((Canvas) (mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            double offsetX = mouseEvent.getSceneX() - orgSceneX;
            double offsetY = mouseEvent.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            magnet.centerX = newTranslateX;
            magnet.centerY = newTranslateY;
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
}

