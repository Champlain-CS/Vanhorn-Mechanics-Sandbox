package com.example.mechanicSim;

import javafx.scene.shape.Shape;

public class PhysicsObject {
    private Shape shape;
    private boolean isAnchored = false;
    private double positionX = 0;
    private double positionY = 0;

    PhysicsObject(Shape shape) {
        this.shape = shape;
    }

    PhysicsObject(Shape shape, double positionX, double positionY) {
        this.shape = shape;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    PhysicsObject(Shape shape, boolean isAnchored) {
        this.shape = shape;
        this.isAnchored = isAnchored;
    }

    PhysicsObject(Shape shape, boolean isAnchored, double positionX, double positionY) {
        this.shape = shape;
        this.isAnchored = isAnchored;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /* --- Getters and Setters */
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
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
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}
