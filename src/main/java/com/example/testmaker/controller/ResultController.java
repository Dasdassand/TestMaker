package com.example.testmaker.controller;

import com.example.testmaker.data.DataBaseAPI;
import com.example.testmaker.entety.Answers;
import com.example.testmaker.entety.StudentResult;
import com.example.testmaker.entety.Test;
import com.example.testmaker.entety.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private TextArea Text;
    private List<StudentResult> studentResults = new ArrayList<>();


    @FXML
    void initialize() throws IOException, SQLException {
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'See.fxml'.";
        assert Text != null : "fx:id=\"Text\" was not injected: check your FXML file 'See.fxml'.";
        ResultSet student_id = DataBaseAPI.getDataBase().getResultSet("Select student_id From StudentTest");
        while (student_id.next()) {
            studentResults.add(new StudentResult(student_id.getInt(1)));
        }
        for (StudentResult s :
                studentResults) {
           s.setCount(checkTest(s.getId()));
           s.setUsername(DataBaseAPI.getDataBase().getResultSet("Select username from Student Where id = " +s.getId())
                   .getString(1));
        }
        Text.setText(studentResults.toString());
    }

    private int checkTest(int id) throws IOException, SQLException {
        int res = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        ResultSet resultSet = DataBaseAPI.getDataBase().getResultSet("Select test_id, answer From StudentTest Where student_id = "
                + id + ";");
        List<Answers> answersStudent = objectMapper.readValue(resultSet.getString(2), objectMapper.getTypeFactory().constructCollectionType(List.class, Answers.class));
        List<Answers> answersTrue = objectMapper.readValue(DataBaseAPI.getDataBase()
                .getResultSet("Select test_json From Test Where id = " + "'" + resultSet.getString(1)+ "'")
                .getString(1), objectMapper.getTypeFactory().constructCollectionType(List.class, Answers.class));
        for (int i = 0; i < answersStudent.size(); i++) {
            if (answersStudent.get(i).getAnswers().equals(answersTrue.get(i).getAnswers()))
                res++;
        }
        return res;
    }
}
