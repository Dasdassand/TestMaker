package com.example.testmaker.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.testmaker.data.TemporaryMemory;
import com.example.testmaker.security.Security;

import com.example.testmaker.server.ServerApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AuthController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button But;

    @FXML
    private TextField Login;

    @FXML
    private TextField Password;

    @FXML
    void initialize() throws UnknownHostException {
        assert But != null : "fx:id=\"But\" was not injected: check your FXML file 'Untitled'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'Untitled'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'Untitled'.";
        Login.setText("");
        Password.setText("");
        But.setOnAction(actionEvent -> {
            if (Login.getText().equals("") || Password.getText().equals(""))
                OtherController.generateAlert("Не введены данные", Alert.AlertType.CONFIRMATION);
            else {
                TemporaryMemory.user.setUsername(Login.getText());
                TemporaryMemory.user.setPassword(Password.getText());
                try {
                    if (Security.checkData(TemporaryMemory.user, "Teacher"))
                        OtherController.openWindow("Панель админестратора","AdminForm.fxml","title.png",But);
                     else {
                        OtherController.generateAlert("Введены не верные данные", Alert.AlertType.WARNING);
                        Login.setText("");
                        Password.setText("");
                    }
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }


}
