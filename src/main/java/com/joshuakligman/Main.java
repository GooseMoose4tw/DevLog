package com.joshuakligman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("DevLog is running");

        Button button = new Button("Click Me");
        button.setOnAction(e -> System.out.println("Button Clicked"));


        VBox root = new VBox(10, label, button);

        Scene scene = new Scene(root, 400, 300);


        primaryStage.setTitle("DevLog");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}