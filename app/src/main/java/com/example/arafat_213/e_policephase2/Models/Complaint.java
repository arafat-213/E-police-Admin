package com.example.arafat_213.e_policephase2.Models;


import java.io.Serializable;

public class Complaint implements Serializable {
    private String media;
    private String area, address, description;
    private String username;
    private int status;


    public Complaint() {
    }

    public Complaint(String media, String area, String address, String description, String username, int status) {
        this.media = media;
        this.area = area;
        this.address = address;
        this.description = description;
        this.username = username;
        this.status = status;
    }

    public String getMedia() {
        return media;
    }


    public void setMedia(String media) {
        this.media = media;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "media='" + media + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
