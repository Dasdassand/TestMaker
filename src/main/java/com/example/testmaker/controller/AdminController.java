package com.example.testmaker.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.example.testmaker.data.TemporaryMemory;
import com.example.testmaker.entety.Platoon;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CheckTest;

    @FXML
    private TextField CountQuest;

    @FXML
    private Button CreateButton;

    @FXML
    private Button EnterTest;

    @FXML
    private Button OpenTest;

    @FXML
    private ComboBox<String> SubjectBox;

    @FXML
    private TextField TimeCount;

    @FXML
    void initialize() {
        assert CheckTest != null : "fx:id=\"CheckTest\" was not injected: check your FXML file 'Untitled'.";
        assert CountQuest != null : "fx:id=\"CountQuest\" was not injected: check your FXML file 'Untitled'.";
        assert CreateButton != null : "fx:id=\"CreateButton\" was not injected: check your FXML file 'Untitled'.";
        assert EnterTest != null : "fx:id=\"EnterTest\" was not injected: check your FXML file 'Untitled'.";
        assert OpenTest != null : "fx:id=\"OpenTest\" was not injected: check your FXML file 'Untitled'.";
        assert SubjectBox != null : "fx:id=\"SubjectBox\" was not injected: check your FXML file 'Untitled'.";
        assert TimeCount != null : "fx:id=\"TimeCount\" was not injected: check your FXML file 'Untitled'.";
        SubjectBox.getItems().add("Не выбран");
        SubjectBox.getItems().add("441");
        for (Platoon pl :
                TemporaryMemory.platoons) {
            for (String s :
                    pl.getSubjects()) {
                SubjectBox.getItems().add(pl.getNumber() + " - взвод, " + "дисциплина - " + s);
            }
        }
        SubjectBox.getSelectionModel().selectFirst();
            CountQuest.setText("");
            TimeCount.setText("");
            CreateButton.setOnAction(actionEvent -> {
                if (SubjectBox.getValue().equals("Не выбран"))
                    OtherController.generateAlert("Взвод не выбран", Alert.AlertType.WARNING);
                else if (CountQuest.getText().equals("") || TimeCount.getText().equals(""))
                    OtherController.generateAlert("Введены не все значения", Alert.AlertType.WARNING);
                else {
                    TemporaryMemory.test.setCountQuest(Integer.parseInt(CountQuest.getText()));
                    TemporaryMemory.test.setTime(Integer.parseInt(TimeCount.getText()));
                    TemporaryMemory.test.setDateCreated(LocalDateTime.now());
                    TemporaryMemory.test.setSubjectName(subjectName(SubjectBox.getValue()));
                    OtherController.openWindow("Создание тестов", "TestCreated.fxml",
                            "title.png", CreateButton);
                }

            });
            EnterTest.setOnAction(actionEvent -> {
                OtherController.openWindow("Просмотр теста", "See.fxml", "title.png", EnterTest);
                Stage stage = (Stage) EnterTest.getScene().getWindow();
                stage.close();
            });

        CheckTest.setOnAction(actionEvent -> {
            OtherController.openWindow("Проверка теста", "CheckForm.fxml", "title.png", CheckTest);
            Stage stage = (Stage) CheckTest.getScene().getWindow();
            stage.close();
        });

    }

    private Platoon searchPlatoon(String s) {
        int tmp = Integer.parseInt(s.split(" ")[0]);
        for (Platoon pl :
                TemporaryMemory.platoons) {
            if (pl.getNumber() == tmp)
                return pl;
        }
        return null;
    }

    private String subjectName(String s) {
        String[] tmp = s.split(" ");
        String res = "";
        for (int i = 5; i < tmp.length; i++) {
            res += tmp[i];
        }
        return res;
    }

}
