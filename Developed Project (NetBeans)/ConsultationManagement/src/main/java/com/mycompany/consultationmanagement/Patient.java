/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultationmanagement;

/**
 *
 * @author MSI
 */
public class Patient extends Person {

    private String patientId;

    public Patient(String name, String surname, String dateOfBirth, long mobileNum, String patientId) {
        super(name, surname, dateOfBirth, mobileNum);
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        String information = getName() + " " + getSurname() + " " + patientId;
        return information;
    }
    public String fullDetails() {
        String information = "\nPatient's first name - " + getName() +
                "\nPatient's surname - " + getSurname() +
                "\nPatient's date of birth - " + getDateOfBirth() +
                "\nPatient's mobile number - " + getMobileNum() +
                "\nPatient's id - " + patientId;
        return information;
    }

}

