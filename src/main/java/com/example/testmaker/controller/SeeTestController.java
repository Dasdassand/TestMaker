package com.example.testmaker.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.example.testmaker.data.DataBaseAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> comboBox;

    @FXML
    void initialize() throws IOException, SQLException {
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'See.fxml'.";
        assert Text != null : "fx:id=\"Text\" was not injected: check your FXML file 'See.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'See.fxml'.";
        comboBox.getItems().add("Ok");
        comboBox.getItems().addAll(getTests());
        comboBox.setOnAction(actionEvent -> {
            StringBuilder res = new StringBuilder();
            Scanner scanner = null;
            try {
                scanner = new Scanner(new FileReader(new File(getTest(comboBox.getSelectionModel().getSelectedItem()))));
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
            while (scanner.hasNext()) {
                res.append(scanner.nextLine()).append("\n");
            }
            Text.setText(res.toString());
            scanner.close();
        });

        Exit.setOnAction(
                actionEvent -> {
                    OtherController.openWindow("Панель админестратора", "AdminForm.fxml", "title.png", Exit);
                    Stage stage = (Stage) Exit.getScene().getWindow();
                    stage.close();
                }
        );

    }

    private String getTest(String value) throws IOException, SQLException {
        DataBaseAPI base = DataBaseAPI.getDataBase();
        ResultSet set = base.getResultSet("Select path_to_test from test where test.id = " +
                "'"+value.split("&")[1] +"'");
        String res= "";
        if (set.next())
            res =  set.getString(1);
        set.close();
        base.close();
        return res;
    }

    private LinkedList<String> getTests() throws IOException, SQLException {
        DataBaseAPI base = DataBaseAPI.getDataBase();
        ResultSet set = base.getResultSet("Select id, name from test");
        LinkedList<String> res = new LinkedList<>();
        while (set.next()) {
            res.add(set.getString(2) + "&" + set.getString(1));
        }
        set.close();
        base.close();
        return res;
    }

}
