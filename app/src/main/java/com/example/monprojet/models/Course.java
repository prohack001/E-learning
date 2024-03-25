package com.example.monprojet.models;

import java.util.Date;

public class Course {

    private String courseId, libelle, profile, description, category, content, mentor;
    private int stars, nbStudents;
    private Date timeOut;
    private Double oldPrice, newPrice;

    public Course(String courseId, String libelle, String profile, String description, String category, String content, String mentor, int stars, Double oldPrice, Double newPrice, int nbStudents, Date timeOut) {
        this.courseId = courseId;
        this.libelle = libelle;
        this.profile = profile;
        this.description = description;
        this.category = category;
        this.content = content;
        this.mentor = mentor;
        this.stars = stars;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.nbStudents = nbStudents;
        this.timeOut = timeOut;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public int getNbStudents() {
        return nbStudents;
    }

    public void setNbStudents(int nbStudents) {
        this.nbStudents = nbStudents;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }
}
