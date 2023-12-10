package view;

import controller.UserController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GuiUtil;

public class StudentRegistrationView {

    private VBox view;

    private Stage stage;
    private AdminDashboardView adminDashboardView;

    public StudentRegistrationView(Stage stage, AdminDashboardView adminDashboardView) {
        this.stage = stage;
        this.adminDashboardView = adminDashboardView;
        view = new VBox(10);

        // Create form fields
        TextField nameField = new TextField();
        nameField.setPromptText("Username");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button registerButton = new Button("Register");
        registerButton
                .setOnAction(e -> registerStudent(nameField.getText(), emailField.getText(), passwordField.getText()));

        // Add components to the view
        view.getChildren().addAll(new Label("Register New Student"), nameField, emailField, passwordField,
                registerButton);
    }

    private void registerStudent(String name, String email, String password) {
        UserController userController = new UserController();
        try {
            userController.registerUser(name, email, password, "Student", null);
            GuiUtil.showInfo("Student registered successfully.");
            stage.close();
            adminDashboardView.refreshStudentsTable();
        } catch (Exception e) {
            GuiUtil.showError("Registration failed: " + e.getMessage());
        }
    }

    public Parent getView() {
        return view;
    }
}
