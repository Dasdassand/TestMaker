module com.example.testmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
  //  requires hibernate.core;


    opens com.example.testmaker to javafx.fxml;
    exports com.example.testmaker;
    exports com.example.testmaker.controller;
    opens com.example.testmaker.controller to javafx.fxml;
    opens com.example.testmaker.entety to lombok;
}