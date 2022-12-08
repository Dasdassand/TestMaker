package com.example.testmaker.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.testmaker.data.DataBaseAPI;
import com.example.testmaker.data.TemporaryMemory;
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
    private ResultSet resultSet;

    @FXML
    void initialize() throws IOException, SQLException {
        assert CheckTest != null : "fx:id=\"CheckTest\" was not injected: check your FXML file 'Untitled'.";
        assert CountQuest != null : "fx:id=\"CountQuest\" was not injected: check your FXML file 'Untitled'.";
        assert CreateButton != null : "fx:id=\"CreateButton\" was not injected: check your FXML file 'Untitled'.";
        assert EnterTest != null : "fx:id=\"EnterTest\" was not injected: check your FXML file 'Untitled'.";
        assert OpenTest != null : "fx:id=\"OpenTest\" was not injected: check your FXML file 'Untitled'.";
        assert SubjectBox != null : "fx:id=\"SubjectBox\" was not injected: check your FXML file 'Untitled'.";
        assert TimeCount != null : "fx:id=\"TimeCount\" was not injected: check your FXML file 'Untitled'.";
        DataBaseAPI baseAPI = DataBaseAPI.getDataBase();
        resultSet = baseAPI.getResultSet("SELECT P.number, S.name, s.id, P.id FROM Subject" +
                " as S JOIN Platoon P on P.id = S.platoon JOIN Teacher T on T.id =" +
                " S.teacher WHERE T.id = " + TemporaryMemory.user.getId());
        SubjectBox.getItems().add("Не выбран");
        List<String> listPlSub = makeName(resultSet);
        resultSet.close();
        for (String s :
               listPlSub) {
            SubjectBox.getItems().add(s.split("//")[0]);
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
                TemporaryMemory.test.setDate(LocalDateTime.now());
                TemporaryMemory.test.setIdSubject(subjectID(listPlSub.get(SubjectBox.getSelectionModel().getSelectedIndex() + 1)));
                TemporaryMemory.test.setIdPlatoon(platoonID(listPlSub.get(SubjectBox.getSelectionModel().getSelectedIndex() + 1)));
                TemporaryMemory.test.setIdTeacher(TemporaryMemory.user.getId());
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

    private String subjectID(String s) {
        return s.split("//")[1].split(" ")[0];
    }

    private String platoonID(String s) {
        return s.split("//")[1].split(" ")[1];
    }


    private List<String> makeName(ResultSet set) throws SQLException {
        List<String> resList = new ArrayList<>();
        System.out.println(set.getFetchSize());
        while (set.next())
            resList.add(set.getString(1) + " - " + set.getString(2) +
                    "//" + set.getString(3) + " " + set.getString(4));
        return resList;
    }
}
