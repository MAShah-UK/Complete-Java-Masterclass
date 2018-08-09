module academy.learnprogramming.ui {
    // Optional since javafx.controls marks it as transitive.
    // requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    // Optional since academy.learnprogramming.db marks it as transitive.
    // requires academy.learnprogramming.common;
    requires academy.learnprogramming.db;

    opens academy.learnprogramming.ui to javafx.graphics, javafx.fxml;
}