package com.example.mechanicSim;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ArrowTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Pane pane = new Pane();
        Arrow arrow = new Arrow(100, 100, 100, 0, "F");
        pane.getChildren().add(arrow);


        Button increaseSizeBT = new Button("Increase Size");
        Button reduceSizeBT = new Button("Reduce Size");
        Button rotateCWBT = new Button("Rotate Clockwise");
        Button rotateCCWBT = new Button("Rotate Counterclockwise");

        increaseSizeBT.setOnAction(e -> {arrow.setLength(arrow.getLength() + 5);});
        reduceSizeBT.setOnAction(e -> {arrow.setLength(arrow.getLength() - 5);});
        rotateCWBT.setOnAction(e -> {arrow.setRotation(arrow.getRotation() + 5);});
        rotateCCWBT.setOnAction(e -> {arrow.setRotation(arrow.getRotation() - 5);});

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(increaseSizeBT, reduceSizeBT, rotateCWBT, rotateCCWBT);
        buttonBox.setSpacing(10);

        root.setCenter(pane);
        root.setBottom(buttonBox);

        arrow.setLength(150);
        arrow.setRotation(45);


        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Custom Arrow Node");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
