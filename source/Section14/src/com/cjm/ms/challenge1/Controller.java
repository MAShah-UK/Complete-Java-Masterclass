package com.cjm.ms.challenge1;

import com.cjm.ms.challenge1.datamodel.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView contactsTableView;

    public void initialize() {
        new ContactData();
    }
}
