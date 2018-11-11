package com.company;

import java.io.IOException;
import java.sql.*;

public class Main {

    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/cinema"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    public static void main(String[] args) throws IOException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
