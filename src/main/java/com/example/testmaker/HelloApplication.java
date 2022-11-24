package com.example.testmaker;

import com.example.testmaker.data.DataBaseAPI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

    public static void main(String[] args) {
        launch();
    }
}