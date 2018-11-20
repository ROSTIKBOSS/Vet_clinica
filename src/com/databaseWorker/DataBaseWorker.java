package com.databaseWorker;

import javafx.util.Pair;

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

    public DataBaseWorker() throws IOException {
        FileInputStream fis;
        Properties properties = new Properties();
        fis = new FileInputStream("src/config.properties");
        properties.load(fis);
        username = properties.getProperty("db.login");
        url = properties.getProperty("db.host");
        password = properties.getProperty("db.password");
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
            this.connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean selectDoctor(ArrayList<Doctor> doctorList) {
        try {
            String query = "select * from doctor order by FIO asc";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                doctorList.add(
                        new Doctor(
                                res.getString("FIO"),
                                res.getString("tel_num"),
                                res.getInt("cabinet")
                        )
                );
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean selectSchedule(ArrayList<Pair <Integer, Schedule>> schedules)
    {
        try {
            String query = "select doctor_id, " +
                    "hour(start_time) as shr, minute(start_time) as smn, second(start_time) as ssec, " +
                    "hour(finish_time) as ehr, minute(finish_time) as emn, second(finish_time) as esec, " +
                    "day " +
                    "from schedule " +
                    "order by doctor_id asc;";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                schedules.add(
                        new Pair < >(
                                res.getInt("doctor_id"),
                                new Schedule(res.getString("day"),
                                        res.getInt("shr"),
                                        res.getInt("smn"),
                                        res.getInt("ssec"),
                                        res.getInt("ehr"),
                                        res.getInt("emn"),
                                        res.getInt("esec")
                                )
                        )
                );
            }
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean insertToDoctor(String FIO, int cabinet, String tel_num) {
        if (FIO.length() > 50) {
            FIO = FIO.substring(0, 50);
        }
        if (tel_num.length() > 10) {
            tel_num = tel_num.substring(0, 10);
        }
        try {
            String query = "INSERT INTO doctor (FIO, cabinet, tel_num) " + "VALUES (?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, FIO);
            pst.setString(2, "" + cabinet);// Костыль
            pst.setString(3, tel_num);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteDoctor(String FIO, int cabinet, String tel_num)
    {
        try {
            String query = "SELECT id FROM doctor WHERE FIO = \"" + FIO + "\" and cabinet = " + cabinet + " and tel_num = \"" + tel_num + "\"";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet res = pst.executeQuery(query);
            int id = -1;
            while (res.next())
            {
                id = res.getInt("id");
            }
            if(id==-1)
                return false;
            query = "DELETE FROM doctor WHERE id = " + id;
            pst = connection.prepareStatement(query);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean insertToSchedule(Schedule sch, int docId)
    {
        try {
            String query = "INSERT INTO schedule (day, start_time, finish_time, doctor_id) values (?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, sch.getDay());
            pst.setString(2, sch.getStartTime());
            pst.setString(3, sch.getEndTime());
            pst.setString(4, ""+docId);
            pst.executeUpdate();
            return true;
        }catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}