package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import model.UniversityApplication;

public class ApplicationDAO {
    public List<UniversityApplication> getAllApplications() {
        List<UniversityApplication> applicationList = new ArrayList<>();
        String sql = "SELECT * FROM UniversityApplications";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UniversityApplication app = new UniversityApplication();
                app.setApplicationId(rs.getInt("application_id"));
                app.setUserId(rs.getInt("user_id"));
                app.setType(rs.getString("type"));
                app.setStatus(rs.getString("status"));
                app.setDetails(rs.getString("details"));
                app.setApplicationDate(rs.getDate("application_date"));
                applicationList.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationList;
    }

    public List<UniversityApplication> getAllApplicationsForStudent(int studentId) {
        List<UniversityApplication> applicationList = new ArrayList<>();
        String sql = "SELECT * FROM UniversityApplications where user_id = " + studentId;
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UniversityApplication app = new UniversityApplication();
                app.setApplicationId(rs.getInt("application_id"));
                app.setUserId(rs.getInt("user_id"));
                app.setType(rs.getString("type"));
                app.setStatus(rs.getString("status"));
                app.setDetails(rs.getString("details"));
                app.setApplicationDate(rs.getDate("application_date"));
                applicationList.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationList;
    }    

    public void updateApplicationStatus(int applicationId, String newStatus) {
        String sql = "UPDATE UniversityApplications SET status = ? WHERE application_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, applicationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void submitNewApplication(int studentId, String type, String details) {
        String sql = "INSERT INTO UniversityApplications (user_id, type, details, status, application_date) VALUES (?, ?, ?, 'Pending', CURRENT_DATE)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            pstmt.setString(2, type);
            pstmt.setString(3, details);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}