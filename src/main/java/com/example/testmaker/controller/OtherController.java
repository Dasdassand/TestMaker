package com.example.testmaker.controller;

import com.example.testmaker.HelloApplication;
import com.example.testmaker.data.TemporaryMemory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.example.testmaker.data.TemporaryMemory.test;
import static com.example.testmaker.data.TemporaryMemory.user;


public class OtherController {

    public static void generateAlert(String alertMessage, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public static void openWindow(String stageName, String formName, String imageName, Button button) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(HelloApplication.class.getResource(formName)));
            Scene scene = new Scene(fxmlLoader.load(), -1, -1);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream(imageName))));
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

 /**

*/

    public static File readFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enter file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(new Stage());
    }
}
