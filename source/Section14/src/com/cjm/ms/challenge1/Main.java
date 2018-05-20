package com.cjm.ms.challenge1;

import com.cjm.ms.challenge1.datamodel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("My Contacts");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Override
    public void stop() {
        ContactData.getInstance().saveContacts();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
