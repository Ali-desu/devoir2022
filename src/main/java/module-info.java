module org.example.devoirblanc {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    // Export the controllers package to javafx.fxml
    exports org.example.devoirblanc.controllers to javafx.fxml;

    // Export the models package to Jackson
    exports org.example.devoirblanc.models to com.fasterxml.jackson.databind;

    // Open the models package to Jackson for reflective access
    opens org.example.devoirblanc.models to com.fasterxml.jackson.databind;

    // Open the controllers package to javafx.fxml
    opens org.example.devoirblanc.controllers to javafx.fxml;

    // Export your main package if required by other modules
    exports org.example.devoirblanc;
}

