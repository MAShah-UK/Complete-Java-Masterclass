module academy.learnprogramming.ui {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires academy.learnprogramming.common;
    requires academy.learnprogramming.db;

    opens academy.learnprogramming.ui to javafx.graphics, javafx.fxml;
}