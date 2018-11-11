package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static String username;
    private static String password;
    private static String url;


    private static void _init_() throws IOException {
        FileInputStream fis;
        Properties properties = new Properties();
        fis = new FileInputStream("src/config.properties");
        properties.load(fis);
        username = properties.getProperty("db.login");
        url = properties.getProperty("db.host");
        password = properties.getProperty("db.password");
    }

    public static void main(String[] args) throws IOException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        _init_();
        Connection connection = DriverManager.getConnection(url,username,password);

        String query = "select sername, name, num from client order by sername asc;\n";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()){
            String sername = res.getString("sername");
            String name = res.getString("name");
            int num = res.getInt("num");
            System.out.format("%s, %s, %s\n", sername,name,num);
        }
    }
}
