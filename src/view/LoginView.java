package view;

import java.sql.SQLException;
import java.util.Optional;

import controller.LoginController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UniversityUser;
import util.GuiUtil;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class LoginView {

    private VBox view;

    public LoginView() {
        view = new VBox(10);
        // Components
        Label label = new Label("Login");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        view.getChildren().addAll(label, usernameField, passwordField, loginButton);

        // Add action to login button
        loginButton.setOnAction(e -> {
            loginButton.setDisable(true);
            String username = usernameField.getText();
            String password = passwordField.getText();

            LoginController controller = new LoginController();
            UniversityUser user;
            try {
                user = controller.loginUser(username, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
                user = null;
            }

            if (user != null) {
                GuiUtil.showInfo("Login Successful");
                if ("Admin".equals(user.getRole())) {
                    AdminDashboardView adminDashboard = new AdminDashboardView();
                    Scene adminScene = new Scene(adminDashboard.getView(), 800, 600);
                    Stage adminStage = new Stage();
                    adminStage.setScene(adminScene);
                    adminStage.show();
                } else if ("Student".equals(user.getRole())) {
                    StudentDashboardView studentDashboard = new StudentDashboardView(user.getUserId());
                    Scene studentScene = new Scene(studentDashboard.getView(), 800, 600);
                    Stage studentStage = new Stage();
                    studentStage.setScene(studentScene);
                    studentStage.show();
                }
            } else {
                // Handle login failure
                GuiUtil.showError("Login failed");
            }

            loginButton.setDisable(false);

        });
    }

    public Parent getView() {
        return view;
    }
}
