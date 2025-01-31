package com.example.mechanicSim;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class Physics {
    // Physical constants
    public static final double GRAV_ACC = 9.8;

    // Physics objects present in the scene
    private static final ArrayList<PhysicsObject> PHYSICS_OBJECTS = new ArrayList<>();

    static AnimationTimer gravityTimer;

    /* TODO:
       Transform pixel coordinates to world space coordinates so that we can change the scale of the scene.
       Right now, one pixel represents one meter, so we're REALLY zoomed out and so everything looks
       like it's moving super slowly. In reality, things are moving at the desired speed, it's just as
       if we're looking at very large objects falling hundreds of meters from the sky from very far away.
       We need linear algebra for this.
     */
    public static void update() {
        Timeline updateLoop = new Timeline();
        updateLoop.setCycleCount(Timeline.INDEFINITE);

        // Using an array here so that we can update the value within the lambda function.
        final long[] currentTime = {System.currentTimeMillis()}; // Start time

        KeyFrame frame = new KeyFrame(
                Duration.seconds(0.001), // Enter the desired frame time here (0.016 for 60FPS)
                event -> {
                    long newTime = System.currentTimeMillis();
                    long elapsedTime = newTime - currentTime[0];
                    currentTime[0] = newTime;

                    // Update all physics objects
                    for (PhysicsObject object : PHYSICS_OBJECTS) {
                        if(!object.isAnchored()) {
                            object.updateVelocity(elapsedTime);
                            object.updatePosition(elapsedTime);
                        }
                    }
                }
        );

        updateLoop.getKeyFrames().add(frame);
        updateLoop.play();
    }

    public static void addPhysicsObject(PhysicsObject physicsObject) {
        PHYSICS_OBJECTS.add(physicsObject);
    }
}
