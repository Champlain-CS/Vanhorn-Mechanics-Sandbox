package com.example.mechanicSim;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static UserInterface ui;

    @Override
    public void start(Stage primaryStage) {
         ui = new UserInterface(
                primaryStage,
                600,
                400,
                "Draggable Shapes with Creation and Removal"
        );

         ObjectManipulation.setupObjectCreationCallback();
         Physics.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
