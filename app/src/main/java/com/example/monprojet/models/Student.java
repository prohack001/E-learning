package com.example.monprojet.models;

public class Student extends User{
    private final String studentId, school, level;

    public Student(String studentId, String school, String level) {
        this.studentId = studentId;
        this.school = school;
        this.level = level;
    }

    public Student(String userId, String nom, String prenom, String email, String password, String phone, String profile, String lastMessage, String studentId, String school, String level) {
        super(userId, nom, prenom, email, password, phone, profile, lastMessage);
        this.studentId = studentId;
        this.school = school;
        this.level = level;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSchool() {
        return school;
    }

    public String getLevel() {
        return level;
    }


    public void loginAtCourse(Course course){

    }

    public void logoutAtCourse(Course course){

    }
}
