module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.example;
    exports org.example.controller;
    exports org.example.model;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    opens org.example.model to javafx.base;
}
