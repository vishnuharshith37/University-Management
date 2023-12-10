package dao;

import db.DatabaseConnection;
import model.UniversityUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void saveUser(UniversityUser newUser) throws SQLException {
        String sql = "INSERT INTO UniversityUsers (name, email, hashed_password, salt, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newUser.getName());
            pstmt.setString(2, newUser.getEmail());
            pstmt.setString(3, newUser.getPasswordHash());
            pstmt.setString(4, newUser.getSalt());
            pstmt.setString(5, newUser.getRole());

            pstmt.executeUpdate();
        }
    }

    public UniversityUser findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM UniversityUsers WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UniversityUser user = new UniversityUser();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("hashed_password"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }
        return null;
    }

    public List<UniversityUser> getAllUsers() {
        List<UniversityUser> userList = new ArrayList<>();
        String sql = "SELECT * FROM UniversityUsers WHERE role = 'Student'";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UniversityUser user = new UniversityUser();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                // Don't fetch password hash and salt for security
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM UniversityUsers WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
