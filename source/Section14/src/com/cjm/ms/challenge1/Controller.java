package com.cjm.ms.challenge1;

import com.cjm.ms.challenge1.datamodel.Contact;
import com.cjm.ms.challenge1.datamodel.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
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
}
