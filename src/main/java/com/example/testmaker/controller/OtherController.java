package com.example.testmaker.controller;

import com.example.testmaker.HelloApplication;
import com.example.testmaker.data.TemporaryMemory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


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

    public static void read(String path) throws IOException {
        File file = new File(path, TemporaryMemory.filename + ".txt");
        boolean flag = true;
        while (flag) {
            if (!file.createNewFile()) {
                generateAlert("Файл с таким именем существует - невозможно создать новый", Alert.AlertType.ERROR);
                openWindow("Создание нвого имени", "NameFileForm.fxml", "title.png", new Button());
            } else {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(generateText());
                fileWriter.flush();
                flag = false;
            }
        }
    }

    public static String generateText() {
        return TemporaryMemory.test.toString();
    }
}