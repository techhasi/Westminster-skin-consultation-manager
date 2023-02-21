package com.company;
import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String firstName;
    private final String lastName;
    private final String dob;
    private final String mobileNo;

    public Person(String firstName, String lastName, String dob, String mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mobileNo = mobileNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

}
