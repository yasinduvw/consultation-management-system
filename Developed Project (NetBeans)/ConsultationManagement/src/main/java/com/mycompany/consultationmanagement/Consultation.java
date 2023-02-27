/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultationmanagement;

/**
 *
 * @author MSI
 */
public class Consultation{

    private String date;    //foramt dd-mm-yyyy
    private int startTime;       //format 0000       or use any java API java.startTime package
    private int endTime;
    private Doctor doctor;
    private Patient patient;
    private double cost;
    private String notes;

    public Consultation(String date, int startTime, int endTime, Doctor doctor, Patient patient, double cost, String notes) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor = doctor;
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        String information = patient.toString() + " " + getDate() + " " + getStartTime() + "-" + getEndTime();
        return information;
    }

    public String fullDetails() {
        String information = "\nConsultation Date - " + getDate() +
                "\nConsultation Start Time - " + getStartTime() +
                "\nConsultation End Time - " + getEndTime() +
                "\n" + doctor.fullDetails() +
                "\n" + patient.fullDetails() +
                "\n\nConsultation Cost - " + getCost() + " £" +
                "\nConsultation Notes - ";
        return information;
    }
}

