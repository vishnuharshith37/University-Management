package controller;

import java.sql.SQLException;
import java.util.List;

import dao.UserDAO;
import model.UniversityUser;
import util.PasswordUtil;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(String name, String email, String password, String role, String department)
            throws SQLException {
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);

        // Create a new user object
        UniversityUser newUser = new UniversityUser();
        newUser.setName(name.toLowerCase());
        newUser.setEmail(email);
        newUser.setRole(role);
        newUser.setSalt(salt);
        newUser.setPasswordHash(hashedPassword);

        // Save newUser to the database using UserDAO
        userDAO.saveUser(newUser);
    }

    public List<UniversityUser> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
