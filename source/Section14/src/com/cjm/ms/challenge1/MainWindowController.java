package com.cjm.ms.challenge1;

import com.cjm.ms.challenge1.datamodel.Contact;
import com.cjm.ms.challenge1.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        ((TableColumn<Contact, String>) contactsTableView.getColumns().get(0))
                .setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ((TableColumn<Contact, String>) contactsTableView.getColumns().get(1))
                .setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ((TableColumn<Contact, String>) contactsTableView.getColumns().get(2))
                .setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        ((TableColumn<Contact, String>) contactsTableView.getColumns().get(3))
                .setCellValueFactory(new PropertyValueFactory<>("notes"));
        ContactData.getInstance().loadContacts();
        contactsTableView.setItems(ContactData.getInstance().getContacts());

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
    @FXML
    public void handleContactsNewMenuItem() {
        contactInfoDialog.setTitle("Add New Contact");

        Optional<ButtonType> result = contactInfoDialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Contact newContact = contactInfoDialogController.getContactInfo();
            ContactData.getInstance().addContact(newContact);
            contactsTableView.getSelectionModel().select(newContact);
        }
    }
    @FXML
    public void handleContactsEditMenuItem() {
        contactInfoDialog.setTitle("Edit Contact");
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
