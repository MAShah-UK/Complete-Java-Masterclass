package com.cjm.ms.javafxconcurrency;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
    private Task<ObservableList<String>> task;

    @FXML
    private ListView listView;

    public void initialize() {
        task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                // Simulates retrieving data from database.
                Thread.sleep(1000);
                ObservableList<String> employees = FXCollections.observableArrayList(
                        "Tim Buchalka",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald");
//                // This code can be avoided by binding the ListView's itemsProperty to the task's valueProperty.
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.setItems(employees);
//                    }
//                });
                return employees;
            }
        };
        listView.itemsProperty().bind(task.valueProperty());
    }
    @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }
}
