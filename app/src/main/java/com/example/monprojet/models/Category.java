package com.example.monprojet.models;

public class Category {
    private String categoryId, libelle, profile;

    public Category(String categoryId, String libelle, String profile) {
        this.categoryId = categoryId;
        this.libelle = libelle;
        this.profile = profile;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
}
