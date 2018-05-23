package com.cjm.ms.challenge;

import com.cjm.ms.challenge.datamodel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContactInfoDialogController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField noteTextField;

    public Contact getContactInfo() {
        return new Contact(firstNameTextField.getText(),
                lastNameTextField.getText(),
                phoneNumberTextField.getText(),
                noteTextField.getText());
    }

    public void setContactInfo(Contact contact) {
        firstNameTextField.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        phoneNumberTextField.setText(contact.getPhoneNumber());
        noteTextField.setText(contact.getNotes());
    }
}
