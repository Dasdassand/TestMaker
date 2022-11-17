package com.example.testmaker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.testmaker.data.TemporaryMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameFileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NewName;

    @FXML
    private Button Save;

    @FXML
    void initialize() {
        assert NewName != null : "fx:id=\"NewName\" was not injected: check your FXML file 'Untitled'.";
        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'Untitled'.";
        NewName.setText("");
        Save.setOnAction(actionEvent -> {
            if (NewName.getText().equals(""))
                OtherController.generateAlert("Введите данные", Alert.AlertType.WARNING);
            else {
                TemporaryMemory.filename = NewName.getText();
                Stage stage = (Stage) Save.getScene().getWindow();
                stage.close();
            }
        });
    }

}
