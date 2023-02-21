package com.company;

public class Patient extends Person {
    private String patientId;

    public Patient(String firstName, String lastName, String dob, String mobileNo, String patientId) {
        super(firstName, lastName, dob, mobileNo);
        this.patientId = patientId;
    }
}
