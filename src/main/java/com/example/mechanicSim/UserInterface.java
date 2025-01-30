package com.example.mechanicSim;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserInterface {
    private final Pane MAIN_PANE = new Pane();
    private final HBox BOTTOM_BAR = new HBox();

    private Rectangle rectangleButton;
    private Circle circleButton;
    private Line lineButton;

    // "Draggable Shapes with Creation and Removal"
    UserInterface(Stage primaryStage, int windowWidth, int windowHeight, String windowTitle) {
        // Pane for draggable shapes
        MAIN_PANE.setPrefSize(windowWidth, windowHeight - (double)windowHeight / 8);
        setupToolBar();

        VBox rootLayout = new VBox(MAIN_PANE, BOTTOM_BAR);

        setupScene(primaryStage, rootLayout, windowWidth, windowHeight, windowTitle);
    }

    private void setupScene(Stage primaryStage, Pane root, int windowWidth, int windowHeight, String windowTitle) {
        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setTitle(windowTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Set up the app's toolbar
    private void setupToolBar() {
        rectangleButton = createButtonRectangle(100, 30, Color.WHITE);
        circleButton = createButtonCircle(15, Color.GRAY);
        lineButton = createButtonLine(50, 5, Color.BLACK);

        // Bottom bar for shape buttons
        BOTTOM_BAR.setPrefHeight(50);
        BOTTOM_BAR.setStyle("-fx-background-color: lightgray;");
        BOTTOM_BAR.setSpacing(10);

        // Add buttons to the bottom bar
        BOTTOM_BAR.getChildren().addAll(rectangleButton, circleButton, lineButton);
    }

    /* --- Helper methods --- */

    // Helper method to create a rectangle button
    private Rectangle createButtonRectangle(double width, double height, Color color) {
        Rectangle rect = new Rectangle(width, height, color);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    // Helper method to create a circle button
    private Circle createButtonCircle(double radius, Color color) {
        Circle circle = new Circle(radius, color);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    // Helper method to create a line button
    private Line createButtonLine(double length, double strokeWidth, Color color) {
        Line line = new Line(0, 0, length, 0);
        line.setStroke(color);
        line.setStrokeWidth(strokeWidth);
        return line;
    }

    /* --- Getters and Setters --- */
    public Pane getMainPane() {
        return MAIN_PANE;
    }

    public Rectangle getRectangleButton() {
        return rectangleButton;
    }

    public Circle getCircleButton() {
        return circleButton;
    }

    public Line getLineButton() {
        return lineButton;
    }
}
