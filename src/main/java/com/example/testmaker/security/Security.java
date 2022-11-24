package com.example.testmaker.security;

import com.example.testmaker.entety.User;
import com.example.testmaker.data.DataBaseAPI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Security {
    public static boolean checkData(User user) throws IOException, SQLException {
        ResultSet resultSet = DataBaseAPI.getDataBase().getResultSet("Select username, password, id FROM Teacher");
        while (resultSet.next()){
            if (resultSet.getString(1).equals(user.getUsername()) && resultSet.getString(2).equals(
                    user.getPassword())){
                user.setId(resultSet.getString(3));
                resultSet.close();
                DataBaseAPI.getDataBase().close();
                return true;
            }
        }
        return false;
    }
}
