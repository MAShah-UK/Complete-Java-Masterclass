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
    private TableView contactsTableView;

    public void initialize() {
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
    }
    @FXML
    public void handleContactsNewMenuItem() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindowRoot.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ContactInfoDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e) {
            System.out.println("Couldn't load the dialog: ");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            ContactInfoDialogController controller = fxmlLoader.getController();
            Contact newContact = controller.getContactInfo();
            ContactData.getInstance().addContact(newContact);
            contactsTableView.getSelectionModel().select(newContact);
        }
    }
    @FXML
    public void handleContactsEditMenuItem() {

    }
    @FXML
    public void handleContactsDeleteMenuItem() {

    }
    @FXML
    public void handeContactsExitMenuItem() {
        Platform.exit();
    }
}
