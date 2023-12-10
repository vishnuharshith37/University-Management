package view;

import java.util.List;

import controller.ApplicationController;
import controller.UserController;
import dao.ApplicationDAO;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UniversityApplication;
import util.GuiUtil;

public class StudentDashboardView {

    private VBox view;
    private int studentId;
    TableView<UniversityApplication> applicationsTable;
    ApplicationController applicationController;

    public StudentDashboardView(int studentId) {

                applicationController = new ApplicationController();
        this.studentId = studentId;
        view = new VBox(10);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> GuiUtil.logout());

        Button createApplicationButton = new Button("Create New Application");
        createApplicationButton.setOnAction(e -> openApplicationForm());

        

        // Table for viewing application statuses
        TableView<UniversityApplication> applicationsTable = createApplicationsTable();

        // Add components to the view
        view.getChildren().addAll(logoutButton, createApplicationButton, applicationsTable);
    }

    private void openApplicationForm() {
        Stage applicationStage = new Stage();
        ApplicationFormView registrationView = new ApplicationFormView(applicationStage, this, studentId);
        Scene scene = new Scene(registrationView.getView(), 400, 300);
        applicationStage.setScene(scene);
        applicationStage.setTitle("New Application");
        applicationStage.show();
    }    

    // private void submitApplication(String type, String details) {
    //     // ApplicationDAO applicationDAO = new ApplicationDAO();
    //     // applicationDAO.submitNewApplication(studentId, type, details); // Implement this method in ApplicationDAO
    //     // Refresh application status table
    // }



    private TableView<UniversityApplication> createApplicationsTable() {
        applicationsTable = new TableView<>();
        TableColumn<UniversityApplication, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<UniversityApplication, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<UniversityApplication, String> detailsCol = new TableColumn<>("Details");
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details")); 
        
                TableColumn<UniversityApplication, String> dateCol = new TableColumn<>("Application Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("applicationDate")); 
        
        applicationsTable.getColumns().addAll(typeCol, statusCol, detailsCol, dateCol);

        refreshApplicationsTable();
        return applicationsTable;
    }

    public void refreshApplicationsTable() {
        List<UniversityApplication> applicationList = applicationController.getAllApplicationsForStudent(studentId);
        applicationsTable.setItems(FXCollections.observableArrayList(applicationList));
    }    

    public Parent getView() {
        return view;
    }
}
