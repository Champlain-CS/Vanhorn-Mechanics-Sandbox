package com.example.mechanicSim;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class PhysicsObject {
    private Shape shape;

    private boolean isAnchored = false;

    private double positionX = 0;
    private double positionY = 0;

    private double velocityX = 0;
    private double velocityY = 0;
    private double accelerationX = 0;
    private double accelerationY = 0;

    // For dragging with mouse
    private double sceneX;
    private double sceneY;
    private double translateX;
    private double translateY;

    /* --- Object Callbacks --- */
    EventHandler<MouseEvent> objectPressedCallback = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            isAnchored = true;

            // Reset velocity
            velocityX = 0;
            velocityY = 0;

            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            translateX = ((Shape)event.getSource()).getTranslateX();
            translateY = ((Shape)event.getSource()).getTranslateY();
        }
    };

    EventHandler<MouseEvent> objectDraggedCallback = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;

            double newTranslateX = translateX + offsetX;
            double newTranslateY = translateY + offsetY;

            setPositionX(newTranslateX);
            setPositionY(newTranslateY);
        }
    };

    /* --- Constructors --- */
    PhysicsObject(Shape shape) {
        this.shape = shape;

        Physics.addPhysicsObject(this);
        Main.ui.getMainPane().getChildren().add(shape);

        // Set default acceleration (downward)
        accelerationY = Physics.GRAV_ACC;

        unlock();
    }

    PhysicsObject(Shape shape, double positionX, double positionY) {
        this.shape = shape;
        this.positionX = positionX;
        this.positionY = positionY;

        Physics.addPhysicsObject(this);
        Main.ui.getMainPane().getChildren().add(shape);

        // Set default acceleration (downward)
        accelerationY = Physics.GRAV_ACC;

        unlock();
    }

    PhysicsObject(Shape shape, boolean isAnchored) {
        this.shape = shape;
        this.isAnchored = isAnchored;

        Physics.addPhysicsObject(this);
        Main.ui.getMainPane().getChildren().add(shape);

        // Set default acceleration (downward)
        accelerationY = Physics.GRAV_ACC;

        unlock();
    }

    PhysicsObject(Shape shape, boolean isAnchored, double positionX, double positionY) {
        this.shape = shape;
        this.isAnchored = isAnchored;
        this.positionX = positionX;
        this.positionY = positionY;

        Physics.addPhysicsObject(this);
        Main.ui.getMainPane().getChildren().add(shape);

        // Set default acceleration (downward)
        accelerationY = Physics.GRAV_ACC;

        unlock();
    }

    // Update the object's velocity
    public void updateVelocity(long elapsedTime) {
        // Simulate horizontal acceleration (due to some kind of thrust or collision maybe?)
        velocityX = velocityX + accelerationX * (elapsedTime / 1000.0);

        // Simulate gravitational acceleration
        velocityY = velocityY + accelerationY * (elapsedTime / 1000.0);
    }

    // Update the object's position (framerate independent)
    public void updatePosition(long elapsedTime) {
        setPositionX(positionX + velocityX * (elapsedTime / 1000.0));
        setPositionY(positionY + velocityY * (elapsedTime / 1000.0));
    }

    // Setup callbacks to enable dragging and removal of objects.
    private void unlock() {
        shape.setOnMousePressed(objectPressedCallback);

        shape.setOnMouseReleased(event -> {
            isAnchored = false;
        });

        shape.setOnMouseDragged(objectDraggedCallback);

        shape.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                Pane parent = (Pane) shape.getParent();
                parent.getChildren().remove(shape);
            }
        });
    }

    // Empty all callbacks so that the object becomes immovable using the mouse.
    private void lock() {
        shape.setOnMousePressed(event -> {});
        shape.setOnMouseReleased(event -> {});
        shape.setOnMouseDragged(event -> {});
        shape.setOnMouseClicked(event -> {});
    }

    /* --- Getters and Setters */
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        shape.setTranslateX(positionX);
        shape.setTranslateY(positionY);

        this.shape = shape;
    }

    public boolean isAnchored() {
        return isAnchored;
    }

    public void setAnchored(boolean anchored) {
        isAnchored = anchored;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
        this.shape.setTranslateX(positionX);
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
        this.shape.setTranslateY(positionY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }
}
