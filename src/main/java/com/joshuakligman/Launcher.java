package com.joshuakligman;
/**
 * Entry point that launches the application without extending Application.
 * Working around the "JavaFX runtime components are missing" error that occurs
 * when a class extending Application is launched from the classpath.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}