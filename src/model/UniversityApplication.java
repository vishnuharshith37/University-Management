package model;

import java.sql.Date;

public class UniversityApplication {

    private int applicationId;
    private int userId;
    private String type;
    private String status;
    private String details;
    private Date applicationDate;

    public UniversityApplication() {
    }

    public UniversityApplication(int applicationId, int userId, String type, String status, String details,
            Date applicationDate) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.details = details;
        this.applicationDate = applicationDate;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public String toString() {
        return "UniversityApplication [applicationId=" + applicationId + ", userId=" + userId + ", type=" + type
                + ", status=" + status + ", details=" + details + ", applicationDate=" + applicationDate + "]";
    }

}
