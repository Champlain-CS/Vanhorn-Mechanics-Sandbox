package com.example.vanhornmechanicssimulator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GridBackgroundWithButton extends Application {

    private static final double GRAVITY = 9.8; // Gravity acceleration
    private static final double GROUND_LEVEL = 500; // Bottom boundary of the scene
    private List<Rectangle> fallingSquares = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane for free placement of squares
        Pane pane = new Pane();

        // Add a button
        Button addSquareButton = new Button("Add Square");
        addSquareButton.setLayoutX(10);
        addSquareButton.setLayoutY(10);

        // Add button click action
        addSquareButton.setOnAction(e -> {
            // Create a square
            Rectangle square = new Rectangle(50, 50, Color.BLUE);
            square.setStroke(Color.BLACK);
            square.setLayoutX(50 + Math.random() * 400); // Random X position
            square.setLayoutY(0); // Start at the top of the pane

            // Add the square to the pane and list
            pane.getChildren().add(square);
            fallingSquares.add(square);
        });

        // Add the button to the pane
        pane.getChildren().add(addSquareButton);

        // Create a scene
        Scene scene = new Scene(pane, 500, 500);

        // Animation timer to simulate gravity
        AnimationTimer gravityTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Rectangle square : fallingSquares) {
                    // Update position
                    double newY = square.getLayoutY() + GRAVITY;

                    // Check for ground collision
                    if (newY + square.getHeight() >= GROUND_LEVEL) {
                        newY = GROUND_LEVEL - square.getHeight(); // Stop at the ground
                    }

                    // Apply new position
                    square.setLayoutY(newY);
                }
            }
        };

        gravityTimer.start(); // Start the gravity simulation

        // Set up the stage
        primaryStage.setTitle("Gravity Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
