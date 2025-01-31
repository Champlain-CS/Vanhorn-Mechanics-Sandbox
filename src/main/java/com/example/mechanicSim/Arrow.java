package com.example.mechanicSim;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Arrow extends Pane {
    private final Line line;
    private final Polygon arrowhead;
    private final Rotate rotate;
    private double length;
    private final Text forceName;
    private double endX, endY;


    public Arrow(double startX, double startY, double length, double angle, String name) {
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


        //Force Name (Text)
        forceName = new Text(name);
        forceName.setFill(Color.BLACK);
        forceName.setFont(new Font(20));
        updateName();


        // Rotation transform (around the start of the arrow)
        rotate = new Rotate(angle, startX, startY);
        this.getTransforms().add(rotate);

        getChildren().addAll(line, arrowhead, forceName);
    }

    private void updateArrowhead() {
        //Adding a little offset to the endX to remove clipping of the shaft in the arrow head
        endX = line.getEndX()+ 2;
        endY = line.getEndY();
        double arrowSize = 10;

        arrowhead.getPoints().setAll(
                endX, endY,
                endX - arrowSize, endY - arrowSize / 2,
                endX - arrowSize, endY + arrowSize / 2
        );
    }

    private void updateName() {
        forceName.setX(endX+5);
        forceName.setY(endY-10);
        if (rotate != null) {
            forceName.setRotate(0-rotate.getAngle());
        }
    }

    public void setLength(double newLength) {
        length = newLength;
        line.setEndX(line.getStartX() + newLength);
        updateArrowhead();
        updateName();
    }

    public void setRotation(double angle) {
        rotate.setAngle(angle);
        updateName();
    }

    public double getLength() {
        return length;
    }
    public double getRotation() {
        return rotate.getAngle();
    }
}
