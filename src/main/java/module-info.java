module com.example.ati {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ati to javafx.fxml;
    exports com.example.ati;
    exports com.example.ati.controller;
    exports com.example.ati.domain;
    exports com.example.ati.dto;
    opens com.example.ati.controller to javafx.fxml;
    opens com.example.ati.domain to javafx.base;
    opens com.example.ati.dto to javafx.fxml;
}