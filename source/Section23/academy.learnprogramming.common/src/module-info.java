module academy.learnprogramming.common {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;

    exports academy.learnprogramming.ui to javafx.graphics, javafx.fxml;
    opens academy.learnprogramming.ui to javafx.fxml;
    opens academy.learnprogramming.common to javafx.base;
}