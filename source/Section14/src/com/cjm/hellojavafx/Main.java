package com.cjm.hellojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        // Equivalent to .fxml:
//        <GridPane fx:controller="Controller"
//                xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
//            <Label text="Welcome to JavaFX!" textFill="blue">
//                <font>
//                    <Font name="Times New Roman bold" size="70"/>
//                </font>
//            </Label>
//        </GridPane>

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        root.setVgap(10);
//        root.setHgap(10);
//
//        Label greeting = new Label("Welcome to JavaFX!");
//        greeting.setTextFill(Color.BLUE);
//        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//        root.getChildren().add(greeting);

        primaryStage.setTitle("Hello JavaFX");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
