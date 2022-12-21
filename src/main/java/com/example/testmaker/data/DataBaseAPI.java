package com.example.testmaker.data;

import com.example.testmaker.HelloApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class DataBaseAPI {
    {
        properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/com/example/testmaker/config.properties");
            this.properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

    }

    private static DataBaseAPI dataBase;
    private Connection connection;
    private Statement statement;
    private final Properties properties;

    private DataBaseAPI() throws IOException {

    }

    public static DataBaseAPI getDataBase() throws IOException {
        if (dataBase == null)
            dataBase = new DataBaseAPI();
        return dataBase;
    }

    private void openSession() throws SQLException {
        connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
                properties.getProperty("db.password"));
        statement = connection.createStatement();
    }

    public ResultSet getResultSet(String query) throws SQLException {
        openSession();
        return statement.executeQuery(query);
    }

    public void addValue(String query) throws SQLException {
        openSession();
        statement.execute(query);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
