package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML // Connects the instance variable to the FXML nameField object. Otherwise it will be null.
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;

    @FXML // Optional before a method, but indicates that it is related to the FXML file.
    public void initialize() { // initialize() is automatically called.
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
    @FXML
    public void onButtonClicked(ActionEvent e) {
        String prefix = "[?]";
        if(e.getSource().equals(helloButton)) {
            prefix = "Hello";
        } else if (e.getSource().equals(byeButton)) {
            prefix = "Bye";
        }
        System.out.println(prefix + ", " + nameField.getText() + ".");
    }
    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }
}
