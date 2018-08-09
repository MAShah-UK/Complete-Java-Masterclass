module academy.learnprogramming.common {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;

    exports academy.learnprogramming.common;
    opens academy.learnprogramming.common to javafx.base;
}