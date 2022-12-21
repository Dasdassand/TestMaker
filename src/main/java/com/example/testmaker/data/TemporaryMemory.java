package com.example.testmaker.data;

import com.example.testmaker.entety.PersonResult;
import com.example.testmaker.entety.StudentAndTest;
import com.example.testmaker.entety.Test;
import com.example.testmaker.entety.User;
import com.example.testmaker.server.ServerApp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemporaryMemory {
    public static ServerApp serverApp;
    public static String path;
    public static Integer countQuest;
    public static Test test = new Test();
    public static User user = new User();
    public static String fileName = "";
    public static final List<PersonResult> personResult = new ArrayList<>();

    public static final List<User> students = new ArrayList<>();
    public static final List<StudentAndTest> tests = new ArrayList<>();

    public static void addLists() throws IOException, SQLException {
        List<String> testID = new ArrayList<>();
        ResultSet resultSet = DataBaseAPI.getDataBase().getResultSet("SELECT S.id, S.username, S.password FROM student as S, teacher as T, platoon as P\n" +
                "WHERE T.id = " + user.getId() + " AND P.id = S.platoon;");
        ResultSet resultSet1;
        while (resultSet.next()) {
            students.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
        }
        int i = 0;
        for (User u :
                students) {
            tests.add(new StudentAndTest(u));
            resultSet = (DataBaseAPI.getDataBase().getResultSet("SELECT test_id FROM studenttest WHERE student_id = " + u.getId() + " ;"));
            while (resultSet.next()) {
                resultSet1 = DataBaseAPI.getDataBase().getResultSet("Select test_json from test where id = " + "'" + resultSet.getString(1) +"'");
                while (resultSet1.next()){
                    tests.get(i).getTests().add(new ObjectMapper().readValue(resultSet1.getString(1), Test.class));
                }
            }
            i++;
        }
        // q = "SELECT path_to_test FROM test WHERE id =" + "'" + s + "'" + " ;";
        System.out.println(students);
        System.out.println(testID);
        System.out.println(tests);
    }


}
