
module com.example.testmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.testmaker to javafx.fxml;
    exports com.example.testmaker;
    exports com.example.testmaker.controller;
    opens com.example.testmaker.controller to javafx.fxml;
    opens com.example.testmaker.entety to lombok;
    opens com.example.testmaker.data to java.sql;
}
