package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.ApplicationDAO;
import db.DatabaseConnection;
import model.UniversityApplication;

public class ApplicationController {

    ApplicationDAO applicationDAO;

    public ApplicationController() {
        this.applicationDAO = new ApplicationDAO();
    }

    public List<UniversityApplication> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

        public List<UniversityApplication> getAllApplicationsForStudent(int studentId) {
        return applicationDAO.getAllApplicationsForStudent(studentId);
    }

    public void updateApplicationStatus(int applicationId, String newStatus) {
        applicationDAO.updateApplicationStatus(applicationId, newStatus);
    }

    public void newApplication(int studentId, String type, String details) {
        applicationDAO.submitNewApplication(studentId, type, details);
    }
   
}
