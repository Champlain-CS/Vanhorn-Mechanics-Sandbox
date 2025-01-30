package com.example.mechanicSim;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ObjectManipulation {
    public static void setupObjectCreationCallback() {
        // Add click handlers for each button
        Main.ui.getRectangleButton().setOnMouseClicked(event -> {
            Rectangle newRectangle = createDraggableRectangle(100, 50, 150, 100, Color.WHITE);
            Main.ui.getMainPane().getChildren().add(newRectangle);
        });

        Main.ui.getCircleButton().setOnMouseClicked(event -> {
            Circle newCircle = createDraggableCircle(100, 50, 25, Color.GRAY);
            Main.ui.getMainPane().getChildren().add(newCircle);
        });

        Main.ui.getLineButton().setOnMouseClicked(event -> {
            Line newLine = createDraggableLine(100, 50, 200, 50, Color.BLACK);
            Main.ui.getMainPane().getChildren().add(newLine);
        });
    }

    // Method to create a draggable rectangle
    public static Rectangle createDraggableRectangle(double x, double y, double width, double height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);

        enableDraggingAndRemoval(rectangle);

        return rectangle;
    }

    // Method to create a draggable circle
    public static Circle createDraggableCircle(double x, double y, double radius, Color color) {
        Circle circle = new Circle(x, y, radius, color);
        circle.setStroke(Color.BLACK);

        enableDraggingAndRemoval(circle);

        return circle;
    }

    // Method to create a draggable line
    public static Line createDraggableLine(double startX, double startY, double endX, double endY, Color color) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(color);
        line.setStrokeWidth(8);

        // Enable dragging for a line
        line.setOnMousePressed(event -> {
            line.setUserData(new double[]{
                    event.getSceneX() - line.getStartX(),
                    event.getSceneY() - line.getStartY(),
                    event.getSceneX() - line.getEndX(),
                    event.getSceneY() - line.getEndY()
            });
        });

        line.setOnMouseDragged(event -> {
            double[] offset = (double[]) line.getUserData();
            line.setStartX(event.getSceneX() - offset[0]);
            line.setStartY(event.getSceneY() - offset[1]);
            line.setEndX(event.getSceneX() - offset[2]);
            line.setEndY(event.getSceneY() - offset[3]);
        });

        // Enable right-click removal
        line.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                Pane parent = (Pane) line.getParent();
                parent.getChildren().remove(line);
            }
        });

        return line;
    }

    // Helper method to enable dragging and removal for shapes
    private static void enableDraggingAndRemoval(javafx.scene.Node shape) {
        shape.setOnMousePressed(event -> {
            shape.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
        });

        shape.setOnMouseDragged(event -> {
            double[] offset = (double[]) shape.getUserData();
            if (shape instanceof Rectangle rect) {
                rect.setX(rect.getX() + (event.getSceneX() - offset[0]));
                rect.setY(rect.getY() + (event.getSceneY() - offset[1]));
            } else if (shape instanceof Circle circle) {
                circle.setCenterX(circle.getCenterX() + (event.getSceneX() - offset[0]));
                circle.setCenterY(circle.getCenterY() + (event.getSceneY() - offset[1]));
            }
            shape.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
        });

        // Right click to remove a shape. Dragging and removal of shapes should probably be 2
        // separate methods.
        shape.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                Pane parent = (Pane) shape.getParent();
                parent.getChildren().remove(shape);
            }
        });
    }
}
