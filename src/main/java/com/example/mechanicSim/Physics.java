package com.example.mechanicSim;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Physics {
    public static final double GRAV_ACC_ON_EARTH_AT_SEA_LEVEL = 9.8;
    private static ArrayList<PhysicsObject> PHYSICS_OBJECTS = new ArrayList<>();

    public static void update() {
        // Animation timer to simulate gravity and collisions
        AnimationTimer gravityTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (PhysicsObject object : PHYSICS_OBJECTS) {
                    object.setPositionX(object.getPositionX() + GRAV_ACC_ON_EARTH_AT_SEA_LEVEL);
                }
            }
        };
        gravityTimer.start();
    }

    public static void addPhysicsObject(PhysicsObject physicsObject) {
        PHYSICS_OBJECTS.add(physicsObject);
    }
}
