package com.cjm.ms.hellojavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML // Connects the instance variable to the FXML nameField object. Otherwise it will be null.
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox clearCheckBox;
    @FXML
    private Label ourLabel;

    @FXML // Optional before a method, but indicates that it is related to the FXML file.
    public void initialize() { // initialize() is automatically called.
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
    @FXML
    public void onButtonClicked(ActionEvent e) { // Automatically called when button is clicked.
        String prefix = "[?]";
        if(e.getSource().equals(helloButton)) {
            prefix = "Hello";
        } else if (e.getSource().equals(byeButton)) {
            prefix = "Bye";
        }
        System.out.println(prefix + ", " + nameField.getText() + ".");

        // Runs complicated process on another thread so that the UI doesn't suspend.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Used to debug which thread the code is running on.
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep: " + s);
                    // Complicated process represented by Thread.sleep().
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label: " + s);
                            // UI controls can only be updated on UI thread which runLater() does.
                            ourLabel.setText("We did something.");
                        }
                    });
                } catch(InterruptedException ie) {

                }
            }
        }).start();

        if(clearCheckBox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }
    @FXML
    public void handleKeyReleased() { // Automatically called when text is input into text field.
        String text = nameField.getText();
        boolean disableButtons = text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }
    @FXML
    public void handleChange() { // Automatically called when checkbox is interacted with.
        String postfix = clearCheckBox.isSelected() ? "checked" : "not checked";
        System.out.println("CheckBox is " + postfix + ".");
    }
}
