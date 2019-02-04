package com.example.arafat_213.e_policephase2.Models;

public class Complaint {

    private String complaintType;
    private String complaintContent;
    private String complaintImage;


    public Complaint() {
    }

    public Complaint(String complaintType, String complaintContent, String complaintImage) {
        this.complaintType = complaintType;
        this.complaintContent = complaintContent;
        this.complaintImage = complaintImage;
    }

    public Complaint(String complaintType, String complaintContent) {
        this.complaintType = complaintType;
        this.complaintContent = complaintContent;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public String getComplaintImage() {
        return complaintImage;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public void setComplaintImage(String complaintImage) {
        this.complaintImage = complaintImage;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintType='" + complaintType + '\'' +
                ", complaintContent='" + complaintContent + '\'' +
                ", complaintImage='" + complaintImage + '\'' +
                '}';
    }
}
