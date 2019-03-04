package com.example.arafat_213.e_policephase2.Models;

import java.util.Comparator;

public class Feedback {
    private String policeStation;
    private String policeOfficer;
    private String rating;
    private String description;
    private String userName;
    public static Comparator<Feedback> COMPARE_BY_TIMESTAMP = new Comparator<Feedback>() {
        @Override
        public int compare(Feedback feedback, Feedback t1) {
            return compareLong(feedback.timestamp, t1.timestamp);
        }
    };
    private String policemanID;
    private long timestamp;

    public Feedback() {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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

    public Feedback(String policeStation, String policeOfficer, String rating, String description, String userName, String policemanID, long timestamp) {
        this.policeStation = policeStation;
        this.policeOfficer = policeOfficer;
        this.rating = rating;
        this.description = description;
        this.userName = userName;
        this.policemanID = policemanID;
        this.timestamp = timestamp;
    }

    private static int compareLong(long a, long b) {
        return a > b ? -1
                : a < b ? 1
                : 0;
    }

    public String getPolicemanID() {
        return policemanID;
    }

    public void setPolicemanID(String policemanID) {
        this.policemanID = policemanID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "policeStation='" + policeStation + '\'' +
                ", policeOfficer='" + policeOfficer + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", policemanID='" + policemanID + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}