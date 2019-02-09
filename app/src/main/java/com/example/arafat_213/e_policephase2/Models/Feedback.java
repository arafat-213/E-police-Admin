package com.example.arafat_213.e_policephase2.Models;

public class Feedback {

    private String policeStation;
    private String policeOfficer;
    private float rating;
    private String description;
    private String userName;

    public Feedback() {
    }

    public Feedback(String policeStation, String policeOfficer, float rating, String description, String userName) {
        this.policeStation = policeStation;
        this.policeOfficer = policeOfficer;
        this.rating = rating;
        this.description = description;
        // When anonymous == false
        this.userName = userName;
    }

    public Feedback(String policeStation, String policeOfficer, float rating, String description) {
        this.policeStation = policeStation;
        this.policeOfficer = policeOfficer;
        this.rating = rating;
        this.description = description;
        // When anonymous == true
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getPoliceOfficer() {
        return policeOfficer;
    }

    public void setPoliceOfficer(String policeOfficer) {
        this.policeOfficer = policeOfficer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "policeStation='" + policeStation + '\'' +
                ", policeOfficer='" + policeOfficer + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

}
