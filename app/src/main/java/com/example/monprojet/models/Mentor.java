package com.example.monprojet.models;

public class Mentor extends User {
    private String mentorId, status;
    private int nbCourses, nbStudents;

    public Mentor(String mentorId, String status, int nbCourses, int nbStudents) {
        this.mentorId = mentorId;
        this.status = status;
        this.nbCourses = nbCourses;
        this.nbStudents = nbStudents;
    }

    public Mentor(String userId, String nom, String prenom, String email, String password, String phone, String profile, String lastMessage, String mentorId, String status, int nbCourses, int nbStudents) {
        super(userId, nom, prenom, email, password, phone, profile, lastMessage);
        this.mentorId = mentorId;
        this.status = status;
        this.nbCourses = nbCourses;
        this.nbStudents = nbStudents;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void createCourse(Course course){

    }
    public void updateCourse(Course course){

    }
    public void deleteCourse(String courseId){

    }

}
