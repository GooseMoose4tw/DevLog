package com.joshuakligman.dao;

import com.joshuakligman.model.CodingSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class SessionDAO {

    public void insert(CodingSession session)
    {


        String sql ="""
        INSERT INTO CodingSessions
        (project, language, date, startTime, endTime, duration,
         feature, notes, bugsEncountered, bugsFixed, bugsRemaining, productivity)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try(Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, session.getProject());
            stmt.setString(2, session.getLanguage());
            stmt.setString(3, session.getDate());
            stmt.setString(4, session.getStartTime());
            stmt.setString(5, session.getEndTime());
            stmt.setInt(6, session.getDuration());
            stmt.setString(7, session.getFeature());
            stmt.setString(8, session.getNotes());
            stmt.setString(9, session.getBugsEncountered());
            stmt.setString(10, session.getBugsFixed());
            stmt.setString(11, session.getBugsRemaining());
            stmt.setInt(12, session.getProductivity());

            stmt.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("Failed to insert session: " + e.getMessage());
        }


    }

    public List<CodingSession> getAll()
    {
        List<CodingSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM CodingSessions";

        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()) {
                sessions.add(new CodingSession(
                        rs.getInt("id"),
                        rs.getString("project"),
                        rs.getString("language"),
                        rs.getString("date"),
                        rs.getString("startTime"),
                        rs.getString("endTime"),
                        rs.getInt("duration"),
                        rs.getString("feature"),
                        rs.getString("notes"),
                        rs.getString("bugsEncountered"),
                        rs.getString("bugsFixed"),
                        rs.getString("bugsRemaining"),
                        rs.getInt("productivity")
                ));
            }
        }
        catch(SQLException e){
            System.err.println("Failed to load sessions: " + e.getMessage());
        }

        return sessions;
    }

    public void update(CodingSession session)
    {
        String sql = """
        UPDATE CodingSessions SET
        project = ?, language = ?, date = ?, startTime = ?, endTime = ?,
        duration = ?, feature = ?, notes = ?, bugsEncountered = ?,
        bugsFixed = ?, bugsRemaining = ?, productivity = ?
        WHERE id = ?
        """;

        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, session.getProject());
            stmt.setString(2, session.getLanguage());
            stmt.setString(3, session.getDate());
            stmt.setString(4, session.getStartTime());
            stmt.setString(5, session.getEndTime());
            stmt.setInt(6, session.getDuration());
            stmt.setString(7, session.getFeature());
            stmt.setString(8, session.getNotes());
            stmt.setString(9, session.getBugsEncountered());
            stmt.setString(10, session.getBugsFixed());
            stmt.setString(11, session.getBugsRemaining());
            stmt.setInt(12, session.getProductivity());
            stmt.setInt(13, session.getId());

            stmt.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("Failed to update session: " + e.getMessage());
        }
    }

    public void delete(int id)
    {
        String sql = "DELETE FROM CodingSessions WHERE id = ?";

        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("Failed to delete session: " + e.getMessage());
        }
    }
}
