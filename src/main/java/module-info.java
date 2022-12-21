
module com.example.testmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires netty.all;


    opens com.example.testmaker to javafx.fxml;
    exports com.example.testmaker;
    exports com.example.testmaker.controller;
    exports com.example.testmaker.entety;
    opens com.example.testmaker.data to java.sql;
    opens com.example.testmaker.controller to java.sql, javafx.fxml;
    opens com.example.testmaker.entety;
}
