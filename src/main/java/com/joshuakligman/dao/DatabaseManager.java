package com.joshuakligman.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:devlog.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS CodingSessions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                project TEXT NOT NULL,
                language TEXT NOT NULL,
                date TEXT NOT NULL,
                startTime TEXT,
                endTime TEXT,
                duration INTEGER NOT NULL,
                feature TEXT,
                notes TEXT,
                bugsEncountered TEXT,
                bugsFixed TEXT,
                bugsRemaining TEXT,
                productivity INTEGER NOT NULL
            )
            """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
        }
    }
}