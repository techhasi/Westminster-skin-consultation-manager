package com.company;

public class Doctor extends Person  {
    private final String medicalId;
    private final String specialization;


    public Doctor(String firstName, String lastName, String dob, String mobileNo, String medicalId, String specialization) {
        super(firstName, lastName, dob, mobileNo);
        this.medicalId = medicalId;
        this.specialization = specialization;
    }

    public String getMedicalId() {
        return medicalId;
    }

    public String getSpecialization() {
        return specialization;
    }

}
