package com.example.testmaker.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SeeTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private TextArea Text;

    @FXML
    void initialize() throws FileNotFoundException {
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'See.fxml'.";
        assert Text != null : "fx:id=\"Text\" was not injected: check your FXML file 'See.fxml'.";
        String res = "";
        Scanner scanner = new Scanner(new FileReader(OtherController.readFile()));
        while (scanner.hasNext()) {
            res += scanner.nextLine() + "\n";
        }
        Text.setText(res);
        scanner.close();
        Exit.setOnAction(
                actionEvent -> {
                    OtherController.openWindow("Панель админестратора", "AdminForm.fxml", "title.png", Exit);
                    Stage stage = (Stage) Exit.getScene().getWindow();
                    stage.close();
                }
        );

    }

}
