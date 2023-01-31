module com.example.magnet_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.magnet_java to javafx.fxml;
    exports com.example.magnet_java;
}