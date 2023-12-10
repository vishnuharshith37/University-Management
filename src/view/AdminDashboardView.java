package view;

import java.util.List;
import java.util.Optional;

import controller.ApplicationController;
import controller.UserController;
import dao.ApplicationDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UniversityApplication;
import model.UniversityUser;
import util.GuiUtil;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class AdminDashboardView {

    private VBox view;
    private TabPane tabPane;
    TableView<UniversityUser> studentsTable;
    TableView<UniversityApplication> applicationsTable;

    UserController userController;
    ApplicationController applicationController;

    public AdminDashboardView() {
        userController = new UserController();
        applicationController = new ApplicationController();

        view = new VBox(10);
        tabPane = new TabPane();

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> GuiUtil.logout());

        Button registerStudentButton = new Button("Register New Student");
        registerStudentButton.setOnAction(e -> openStudentRegistrationForm());

        // Tab for managing students
        Tab manageStudentsTab = new Tab("Manage Students");
        manageStudentsTab.setContent(createStudentsTable());
        manageStudentsTab.setClosable(false);

        // Tab for managing applications
        Tab manageApplicationsTab = new Tab("Manage Applications");
        manageApplicationsTab.setContent(createApplicationsTable());
        manageApplicationsTab.setClosable(false);

        // Adding tabs to the TabPane
        tabPane.getTabs().addAll(manageStudentsTab, manageApplicationsTab);

        view.getChildren().add(logoutButton);
        view.getChildren().add(tabPane);
        view.getChildren().add(registerStudentButton);
    }

    private TableView<UniversityUser> createStudentsTable() {
        studentsTable = new TableView<>();
        TableColumn<UniversityUser, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<UniversityUser, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<UniversityUser, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<UniversityUser, Void> deleteCol = new TableColumn<>("Delete");
        deleteCol.setCellFactory(tc -> new TableCell<UniversityUser, Void>() {
            final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(e -> {
                        UniversityUser user = getTableView().getItems().get(getIndex());

                        ButtonType confirm = GuiUtil.showConfirm("Are you sure you want to delete this student?");
                        if (user != null && confirm != null && ButtonType.OK.equals(confirm)) {
                            deleteUser(user);
                            refreshStudentsTable();

                        }
                    });
                }
            }
        });

        studentsTable.getColumns().addAll(nameCol, emailCol, roleCol, deleteCol);

        refreshStudentsTable();
        return studentsTable;
    }

    public void refreshStudentsTable() {
        List<UniversityUser> userList = userController.getAllUsers();
        ObservableList<UniversityUser> data = FXCollections.observableArrayList(userList);
        studentsTable.setItems(data);
    }

    private void deleteUser(UniversityUser user) {
        userController.deleteUser(user.getUserId());
    }

    private void openStudentRegistrationForm() {
        Stage registrationStage = new Stage();
        StudentRegistrationView registrationView = new StudentRegistrationView(registrationStage, this);
        Scene scene = new Scene(registrationView.getView(), 400, 300);
        registrationStage.setScene(scene);
        registrationStage.setTitle("Register New Student");
        registrationStage.show();
    }

    private TableView<UniversityApplication> createApplicationsTable() {
        applicationsTable = new TableView<>();
        TableColumn<UniversityApplication, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<UniversityApplication, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<UniversityApplication, Void> approveCol = new TableColumn<>("Approve");
        approveCol.setCellFactory(tc -> new TableCell<UniversityApplication, Void>() {
            final Button approveButton = new Button("Approve");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    UniversityApplication app = getTableView().getItems().get(getIndex());
                    approveButton.setDisable("Approved".equals(app.getStatus()));
                    setGraphic(approveButton);
                    approveButton.setOnAction(e -> updateApplicationStatus(app.getApplicationId(), "Approved"));
                }
            }
        });

        TableColumn<UniversityApplication, Void> denyCol = new TableColumn<>("Deny");
        denyCol.setCellFactory(tc -> new TableCell<UniversityApplication, Void>() {
            final Button denyButton = new Button("Deny");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    UniversityApplication app = getTableView().getItems().get(getIndex());
                    denyButton.setDisable("Denied".equals(app.getStatus()));
                    setGraphic(denyButton);
                    denyButton.setOnAction(e -> updateApplicationStatus(app.getApplicationId(), "Denied"));
                }
            }
        });

        applicationsTable.getColumns().addAll(typeCol, statusCol, approveCol, denyCol);

        refreshApplicationsTable();
        return applicationsTable;
    }

    private void refreshApplicationsTable() {

        List<UniversityApplication> applicationList = applicationController.getAllApplications();
        applicationsTable.setItems(FXCollections.observableArrayList(applicationList));
    }

    private void updateApplicationStatus(int applicationId, String newStatus) {

        applicationController.updateApplicationStatus(applicationId, newStatus);
        refreshApplicationsTable();
    }

    public Parent getView() {
        return view;
    }
}
