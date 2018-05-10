package com.cjm.ms.javafx_css;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label;
    @FXML
    private Button buttonFour;
    @FXML
    private WebView webView;

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
//        DirectoryChooser chooser = new DirectoryChooser();
//        File file = chooser.showDialog(gridPane.getScene().getWindow());

        // Opens dialog box that allows a user to select a file.
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Application File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                // Can group similar file extensions together.
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"),
                // Catch-all filter for all files. Will need to handle unsupported file types.
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Provide a parent window to make the dialog box modal.
        //File file = chooser.showOpenDialog(gridPane.getScene().getWindow());
        // Opens multiple files.
        //List<File> file = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
        if(file != null) {
            System.out.println(file.getPath());
        } else {
            System.out.println("Chooser was cancelled.");
        }
    }
    @FXML
    public void handleLinkClick() {
        try {
            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
        } catch(IOException|URISyntaxException e) {
            System.out.println("Couldn't open the link: " + e.getMessage());
        }
    }
    @FXML
    public void handleWebViewClick() {
        WebEngine engine = webView.getEngine();
        engine.load("http://www.javafx.com");
    }
}
