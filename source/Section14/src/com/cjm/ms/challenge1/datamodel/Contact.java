package com.cjm.ms.challenge1.datamodel;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Contact {
    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName = new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private SimpleStringProperty notes = new SimpleStringProperty("");

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
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.notes.set(notes);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public void setContactInfo(Contact contact) {
        firstName = contact.firstName;
        lastName = contact.lastName;
        phoneNumber = contact.phoneNumber;
        notes = contact.notes;
    }
}
