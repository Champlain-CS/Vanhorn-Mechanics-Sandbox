package com.example.vanhornmechanicssimulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GridBackgroundWithButton extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true); // Enable grid lines

        // Set the grid background color
        gridPane.setStyle("-fx-background-color: lightgray;");

        // Add a button
        Button addSquareButton = new Button("Add Square");
        gridPane.add(addSquareButton, 0, 0); // Add the button to the grid

        // Add button click action
        addSquareButton.setOnAction(e -> {
            // Create a square
            Rectangle square = new Rectangle(50, 50, Color.BLUE);
            square.setStroke(Color.BLACK);

            // Add the square to the grid
            gridPane.add(square, (int) (Math.random() * 5), (int) (Math.random() * 5));
        });

        // Set constraints for the grid (optional)
        for (int i = 0; i < 5; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(100)); // Columns 100px wide
            gridPane.getRowConstraints().add(new RowConstraints(100)); // Rows 100px tall
        }

        // Create a scene
        Scene scene = new Scene(gridPane, 500, 500);

        // Set up the stage
        primaryStage.setTitle("Grid Background with Button");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
