package com.cjm.ms.challenge1.datamodel;

import java.util.Objects;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String notes;

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (!(o instanceof Contact)) {
            return false;
        }

        Contact other = (Contact) o;
        if(firstName.equals(other.firstName) &&
                lastName.equals(other.lastName) &&
                phoneNumber.equals(other.phoneNumber) &&
                notes.equals(other.notes)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, notes);
    }

    public Contact() {
        this(null, null, null, null);
    }

    public Contact(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
