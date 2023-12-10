package view;
import controller.ApplicationController;
import controller.UserController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GuiUtil;





public class ApplicationFormView {

    private VBox view;
    private int studentId;

    private Stage stage;
    private StudentDashboardView studentDashboardView;

    ApplicationController applicationController;
    public ApplicationFormView(Stage stage, StudentDashboardView studentDashboardView, int studentId) {
        this.studentId = studentId;
        applicationController = new ApplicationController();

        this.stage = stage;
        this.studentDashboardView = studentDashboardView;
        view = new VBox(10);

        // Create form fields
        // Dropdown for application types
        ComboBox<String> applicationTypeDropdown = new ComboBox<>();
        applicationTypeDropdown.getItems().addAll("Leave", "Registration", "Revaluation", "Transcript",
                "AdditionalSlot", "Supplementary");

        // Text area for application details
        TextArea applicationDetails = new TextArea();
        applicationDetails.setPromptText("Enter details here...");

        // // Button to submit application
        Button submitButton = new Button("Submit Application");
        submitButton
                .setOnAction(e -> submitApplication(applicationTypeDropdown.getValue(), applicationDetails.getText()));

        // Add components to the view
        view.getChildren().addAll(new Label("Create New Application"), applicationTypeDropdown, applicationDetails, submitButton);
    }

    private void submitApplication(String type, String details) {
        
        try {
            applicationController.newApplication(studentId, type, details);
            GuiUtil.showInfo("Application created successfully.");
            stage.close();
            studentDashboardView.refreshApplicationsTable();
        } catch (Exception e) {
            GuiUtil.showError("Failed: " + e.getMessage());
        }
    }

    public Parent getView() {
        return view;
    }
}
