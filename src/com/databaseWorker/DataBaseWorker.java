package com.databaseWorker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBaseWorker {

    private static String username;
    private static String password;
    private static String url;
    private static Connection connection;

    public DataBaseWorker() throws IOException{
            FileInputStream fis;
            Properties properties = new Properties();
            fis = new FileInputStream("src/config.properties");
            properties.load(fis);
            username = properties.getProperty("db.login");
            url = properties.getProperty("db.host");
            password = properties.getProperty("db.password");

    }

    public ErrorEnum Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");

            this.connection = DriverManager.getConnection(url, username, password);

            return ErrorEnum.Success;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ErrorEnum.ConnectError;
        }
        catch (SQLException ex) {
            return ErrorEnum.ConnectError;
        }
    }

    public ArrayList<ClientResults> QuerySlectAllClients() {
        try {
            String query = "select FIO, tel_num, salq_id, adr from owner order by FIO asc;\n";

            ArrayList<ClientResults> resultList = new ArrayList<ClientResults>();
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                resultList.add(new ClientResults(
                        res.getString("FIO"),
                        res.getString("tel_num"),
                        res.getInt("salq_id"),
                        res.getString("adr")));
            }
            return resultList;
        } catch (SQLException ex) {
            return new ArrayList<ClientResults>();
        }
    }

    public ArrayList<DoctorResults> QuerySlectAllDcotors(){
        try {
            String query = "select FIO, cabinet, tel_num from doctor order by FIO asc;\n";

            ArrayList<DoctorResults> resultList = new ArrayList<DoctorResults>();
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                resultList.add(new DoctorResults(
                        res.getString("FIO"),
                        res.getString("tel_num"),
                        res.getInt("cabinet")));
            }
            return resultList;
        }
        catch (SQLException sqlEx){
            return new ArrayList<DoctorResults>();
        }
    }

    public boolean CheckTableToEmpty(String tableName){
        try {
            String query = "select * from " + tableName + "; \n";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
               return true;
            }
            return false;
        }
        catch (SQLException sqlEx){
            return false;
        }
    }

    public ErrorEnum InsertToDoctor(int id, String FIO, int cabinet, String tel_num) {
        if(FIO.length() > 50) {
            FIO = FIO.substring(0, 50);
        }
        if(tel_num.length() > 10) {
            tel_num = tel_num.substring(0, 10);
        }
        try {
            String query = "INSERT INTO doctor (id, FIO, cabinet, tel_num)\n" +
                    "VALUES (" + id + ", " + FIO + ", " + cabinet + ", " + tel_num + ");\n";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            return ErrorEnum.Success;
        }
        catch (SQLException ex) {
            return ErrorEnum.InsertError;
        }
    }

    public ErrorEnum InsertToOwner(int id, String FIO, String adr, String tel_num, int salq_id) {
        if(FIO.length() > 50) {
            FIO = FIO.substring(0, 50);
        }
        if(tel_num.length() > 10) {
            tel_num = tel_num.substring(0, 10);
        }
        if(adr.length() > 10) {
            adr = adr.substring(0, 10);
        }
        try {

            String query = "INSERT INTO owner (id, FIO, adr, tel_num, salq_id)\n" +
                    "VALUES (" + id + ", " + FIO + ", " + adr + ", " + tel_num + ", " + salq_id + ");\n";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            return ErrorEnum.Success;
        }
        catch (SQLException ex) {
            return ErrorEnum.InsertError;
        }
    }
}
