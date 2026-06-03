module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.infrastructure;
    opens org.example.infrastructure to javafx.fxml;
}
