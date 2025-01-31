package com.example.mechanicSim;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ObjectManipulation {
    public static void setupObjectCreationCallback() {
        Main.ui.getRectangleButton().setOnMouseClicked(event -> {
            new PhysicsObject(createDraggableRectangle(100, 0, 10, 10, Color.WHITE));
        });

        Main.ui.getCircleButton().setOnMouseClicked(event -> {
            new PhysicsObject(createDraggableCircle(100, 0, 25, Color.GRAY));
        });
    }

    // Method to create a draggable rectangle
    public static Rectangle createDraggableRectangle(double x, double y, double width, double height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);

        return rectangle;
    }

    // Method to create a draggable circle
    public static Circle createDraggableCircle(double x, double y, double radius, Color color) {
        Circle circle = new Circle(x, y, radius, color);
        circle.setStroke(Color.BLACK);

        return circle;
    }
}
