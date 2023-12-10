package controller;

import java.sql.SQLException;

import dao.UserDAO;
import model.UniversityUser;
import util.PasswordUtil;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

    public UniversityUser loginUser(String username, String password) throws SQLException {
        UniversityUser user = userDAO.findUserByUsername(username);

        if (user != null && PasswordUtil.verifyUserPassword(password, user.getPasswordHash(), user.getSalt())) {
            return user; // Login successful
        }
        return null; // Login failed

    }
}
