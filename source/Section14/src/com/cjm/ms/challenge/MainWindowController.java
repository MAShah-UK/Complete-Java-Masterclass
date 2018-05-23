package com.cjm.ms.challenge;

import com.cjm.ms.challenge.datamodel.Contact;
import com.cjm.ms.challenge.datamodel.ContactData;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {
    @FXML
    private VBox mainWindowRoot;
    @FXML
    private TableView<Contact> contactsTableView;

    private Dialog<ButtonType> contactInfoDialog;
    private ContactInfoDialogController contactInfoDialogController;

    public void initialize() {
        // Load contactsTableView data.
        ContactData.getInstance().loadContacts();
        contactsTableView.setItems(ContactData.getInstance().getContacts());
        contactsTableView.getSelectionModel().selectFirst();

        // Define ContactsInfo dialog.
        contactInfoDialog = new Dialog<>();
        //contactInfoDialog.initOwner(mainWindowRoot.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ContactInfoDialog.fxml"));
        try {
            contactInfoDialog.getDialogPane().setContent(loader.load());
        } catch(IOException e) {
            System.out.println("Couldn't load the contactInfoDialog: ");
            e.printStackTrace();
        }
        contactInfoDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        contactInfoDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        contactInfoDialogController = loader.getController();
    }
    public void showNoContactWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Contact Selected");
        alert.setContentText("Please select a contact to edit.");
        alert.showAndWait();
    }
    @FXML
    public void handleContactsNewMenuItem() {
        contactInfoDialog.setTitle("Add New Contact");
        Contact selected = contactsTableView.getSelectionModel().getSelectedItem();
        if(selected != null) {
            contactInfoDialogController.setContactInfo(selected);

            Optional<ButtonType> result = contactInfoDialog.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                Contact newContact = contactInfoDialogController.getContactInfo();
                ContactData.getInstance().addContact(newContact);
                contactsTableView.getSelectionModel().select(newContact);
            }
        } else {
            showNoContactWarning();
        }
    }
    @FXML
    public void handleContactsEditMenuItem() {
        contactInfoDialog.setTitle("Edit Contact");
        Contact selected = contactsTableView.getSelectionModel().getSelectedItem();
        if(selected != null) {
            contactInfoDialogController.setContactInfo(selected);

            Optional<ButtonType> result = contactInfoDialog.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                selected.setContactInfo(contactInfoDialogController.getContactInfo());
                // TODO: There must be a built-in method to update an observable list.
                Contact tmp = new Contact();
                ObservableList<Contact> contacts = ContactData.getInstance().getContacts();
                contacts.add(tmp);
                contacts.remove(tmp);
            }
        } else {
            showNoContactWarning();
        }
    }
    @FXML
    public void handleContactsDeleteMenuItem() {
        Contact selected = contactsTableView.getSelectionModel().getSelectedItem();
        ContactData.getInstance().deleteContact(selected);
    }
    @FXML
    public void handleContactsExitMenuItem() {
        Platform.exit();
    }
}
