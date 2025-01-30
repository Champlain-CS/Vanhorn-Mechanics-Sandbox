package com.example.mechanicSim;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Arrow extends Pane {
    private final Line line;
    private final Polygon arrowhead;
    private final Rotate rotate;
    private double length;

    public Arrow(double startX, double startY, double length, double angle) {
        this.length = length;

        // Line for arrow shaft
        line = new Line(startX, startY, startX + length, startY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5);

        // Arrow head (Triangle)
        arrowhead = new Polygon();
        arrowhead.setScaleX(2);
        arrowhead.setScaleY(2);
        updateArrowhead();

        // Rotation transform (around the start of the arrow)
        rotate = new Rotate(angle, startX, startY);
        this.getTransforms().add(rotate);

        // Add shapes to the pane
        getChildren().addAll(line, arrowhead);
    }

    private void updateArrowhead() {
        //Adding a little offset to the endX to remove clipping of the shaft in the arrow head
        double endX = line.getEndX()+arrowhead.getScaleX();
        double endY = line.getEndY();
        double arrowSize = 10;

        arrowhead.getPoints().setAll(
                endX, endY,
                endX - arrowSize, endY - arrowSize / 2,
                endX - arrowSize, endY + arrowSize / 2
        );
        arrowhead.setFill(Color.BLACK);
    }

    public void setLength(double newLength) {
        length = newLength;
        line.setEndX(line.getStartX() + newLength);
        updateArrowhead();
    }

    public void setRotation(double angle) {
        rotate.setAngle(angle);
    }

    public double getLength() {
        return length;
    }
    public double getRotation() {
        return rotate.getAngle();
    }
}
