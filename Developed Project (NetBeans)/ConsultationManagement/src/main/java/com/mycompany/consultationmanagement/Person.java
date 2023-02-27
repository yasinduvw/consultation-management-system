/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultationmanagement;

/**
 *
 * @author MSI
 */
public class Person {
    private String name;
    private String surname;
    private String dateOfBirth;
    private long mobileNum;

    public Person(String name, String surname, String dateOfBirth, long mobileNum) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNum = mobileNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(int mobileNumber) {
        this.mobileNum = mobileNumber;
    }
}
