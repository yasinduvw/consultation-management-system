/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultationmanagement;

/**
 *
 * @author MSI
 */
public class Doctor extends Person implements Comparable<Doctor>{

    private String licenseNum;
    private String specialisation;

    public Doctor(String name, String surname, String dateOfBirth, long mobileNum, String licenseNum, String specialisation) {
        super(name, surname, dateOfBirth, mobileNum);
        this.licenseNum = licenseNum;
        this.specialisation = specialisation;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    //sorting the arraylist based on alphabetical order of surnames
    @Override
    public int compareTo(Doctor d) {
        return getSurname().compareTo(d.getSurname());
    }

    //displaying information of a record
    @Override
    public String toString() {
        String information = getName() + " " + getSurname() + " " + licenseNum;
        return information;
    }

    public String fullDetails() {
        String information = "\nDoctor's first name - " + getName() +
                "\nDoctor's surname - " + getSurname() +
                "\nDoctor's date of birth - " + getDateOfBirth() +
                "\nDoctor's mobile number - " + getMobileNum() +
                "\nDoctor's license number - " + licenseNum +
                "\nDoctor's specialization - " + specialisation;
        return information;
    }
}
