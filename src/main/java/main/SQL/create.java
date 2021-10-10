package main.SQL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class create {

    public create() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:sqlserver://localhost\\3306/aois;";
            String username = "root";
            String password = "";
            Connection connection;

            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM userInfo;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }


        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
