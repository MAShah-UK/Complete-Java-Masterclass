package com.cjm.ms.javafx_css;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label;
    @FXML
    private Button buttonFour;

    public void initialize() {
        buttonFour.setEffect(new DropShadow());
    }
    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }
    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }
    @FXML
    public void handleClick() {
        // Opens dialog box that allows a user to select a file.
        //FileChooser chooser = new FileChooser();
        // Provide a parent window to make the dialog box modal.
        //chooser.showOpenDialog(gridPane.getScene().getWindow());
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(gridPane.getScene().getWindow());
        if(file != null) {
            System.out.println(file.getPath());
        } else {
            System.out.println("Chooser was cancelled.");
        }
    }
}
