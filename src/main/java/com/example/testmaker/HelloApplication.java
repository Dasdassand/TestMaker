package com.example.testmaker;

import com.example.testmaker.data.DataBaseAPI;

import com.example.testmaker.data.TemporaryMemory;
import com.example.testmaker.entety.PersonResult;
import com.example.testmaker.server.ServerApp;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AuthForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Аунтефекация");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws UnknownHostException {
        String IP = InetAddress.getLocalHost().getHostAddress();
        System.out.println(IP);
        launch();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            for (PersonResult pr :
                   TemporaryMemory.personResult ) {
                DataBaseAPI.getDataBase().addValue("UPDATE StudentTest SET answer = " + "'" + objectMapper.writeValueAsString(pr.getAnswers()) +"'" +" WHERE student_id = " + pr.getUser().getId() + " ;");
            }

        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        TemporaryMemory.serverApp.close();
    }
}