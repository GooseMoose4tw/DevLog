package com.joshuakligman;

import com.joshuakligman.dao.DatabaseManager;

import com.joshuakligman.ui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;

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
        Scene scene = new Scene(mainView.getRoot(), 1100, 700);

        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        primaryStage.setTitle("DevLog - Coding Session Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}