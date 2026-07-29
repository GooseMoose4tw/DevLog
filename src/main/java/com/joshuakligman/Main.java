package com.joshuakligman;

import com.joshuakligman.dao.DatabaseManager;

import com.joshuakligman.ui.MainView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DatabaseManager.createTable();

        MainView mainView = new MainView();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double width = Math.min(1400, bounds.getWidth() * 0.9);
        double height = Math.min(900, bounds.getHeight() * 0.9);

        Scene scene = new Scene(mainView.getRoot(), width, height);

        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        primaryStage.setTitle("DevLog - Coding Session Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}