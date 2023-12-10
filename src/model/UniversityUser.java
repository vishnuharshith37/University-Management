package model;

public class UniversityUser {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private String salt;
    private String role; // 'Student' or 'Admin'

    public UniversityUser(int userId, String name, String email, String passwordHash, String salt, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }

    public UniversityUser() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UniversityUser [userId=" + userId + ", name=" + name + ", email=" + email + ", passwordHash="
                + passwordHash + ", salt=" + salt + ", role=" + role + "]";
    }

}
