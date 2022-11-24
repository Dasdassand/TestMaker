package com.example.testmaker.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

import com.example.testmaker.entety.FileType;
import com.example.testmaker.entety.Type;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CheckFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Check;

    @FXML
    private Button EnterAnswer;

    @FXML
    private Button EnterTest;

    @FXML
    private TextField PathAnswer;

    @FXML
    private TextField PathTest;
    private final List<FileType> tmpFiles = new ArrayList<>();


    @FXML
    void initialize() {
        assert Check != null : "fx:id=\"Check\" was not injected: check your FXML file 'CheckForm.fxml'.";
        assert EnterAnswer != null : "fx:id=\"EnterAnswer\" was not injected: check your FXML file 'CheckForm.fxml'.";
        assert EnterTest != null : "fx:id=\"EnterTest\" was not injected: check your FXML file 'CheckForm.fxml'.";
        assert PathAnswer != null : "fx:id=\"PathAnswer\" was not injected: check your FXML file 'CheckForm.fxml'.";
        assert PathTest != null : "fx:id=\"PathTest\" was not injected: check your FXML file 'CheckForm.fxml'.";
        PathAnswer.setEditable(false);
        PathTest.setEditable(false);
        EnterTest.setOnAction(actionEvent -> {
            tmpFiles.add(new FileType(OtherController.readFile(), Type.Quest));
            PathTest.setText("Получен");

        });
        EnterAnswer.setOnAction(actionEvent -> {
            tmpFiles.add(new FileType(OtherController.readFile(), Type.Answer));
            PathAnswer.setText("Получен");
        });
        Check.setOnAction(actionEvent -> {
            if (!checkLst()) {
                OtherController.generateAlert("Неравное количество тестов и ответов", Alert.AlertType.ERROR);
            } else {
                try {
                    if (checkValidTA()) {
                        System.out.println("Done");
                    } else
                        OtherController.generateAlert("Тесты не соответствуют решениям", Alert.AlertType.WARNING);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        });

    }

    private boolean checkLst() {
        int countT = 0, countA = 0;
        for (FileType f :
                tmpFiles) {
            switch (f.getType()) {
                case Quest -> countT++;
                case Answer -> countA++;
            }

        }
        return countA == countT;
    }

    private boolean checkValidTA() throws FileNotFoundException {
        List<Scanner> scanners = new ArrayList<>();
        for (FileType f :
                tmpFiles) {
            if (f.getType().equals(Type.Quest)) {
                scanners.add(new Scanner(new FileReader(f.getFile())));
            }
        }
        List<String> tmpStr = seeText(scanners);
        List<String> tmpStrTwo = checkKeyAnswer().stream().sorted().toList();
        tmpStr = tmpStr.stream().sorted().toList();
        return tmpStr.equals(tmpStrTwo);
    }

    private List<String> seeText(List<Scanner> scanners) {
        List<String> tmpStr = new ArrayList<>();
        String tmp = "";
        for (Scanner sc :
                scanners) {
            while (sc.hasNext()) {
                tmp += sc.nextLine() + "@" + "\n";
            }
            tmpStr.add(tmp.split("@")[0]);
            tmp = "";
        }
        scanners.stream().close();
        scanners.clear();
        return tmpStr;
    }

    private List<String> checkKeyAnswer() throws FileNotFoundException {
        List<Scanner> scanners = new ArrayList<>();
        for (FileType f :
                tmpFiles) {
            if (f.getType().equals(Type.Answer)) {
                scanners.add(new Scanner(new FileReader(f.getFile())));
            }
        }

        return seeText(scanners);
    }

}
