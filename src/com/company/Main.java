package com.company;

import com.databaseWorker.DataBaseWorker;
import com.databaseWorker.Doctor;
import com.databaseWorker.Schedule;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        DataBaseWorker worker = new DataBaseWorker();

        if(!worker.connect())
            throw new IOException("Failed to connect to database");

        ArrayList<Doctor> doctors = new ArrayList< >();
        ArrayList<Pair<Integer,Schedule>> schedules = new ArrayList< >();

        Schedule sch = new Schedule("monday");

        //if(!worker.insertToSchedule(sch,1))
        //  System.out.println("Insert error");;
        if(!worker.insertToDoctor("abcd", 1, "12345"))
            System.out.println("Insert error");
        if(!worker.deleteDoctor("abcd", 1, "12345"))
            System.out.println("Delete error");

        if(worker.selectDoctor(doctors)) {
            for (Doctor it: doctors) {
                System.out.println(it.FIO);
                System.out.println(it.cabinet);
                System.out.println(it.telephone);
            }
        }
        else
            System.out.println("Select error");
        if(worker.selectSchedule(schedules))
        {
            for(Pair<Integer, Schedule> it: schedules)
            {
                System.out.println(it.getKey());
                System.out.println(it.getValue().getDay());
                System.out.println(it.getValue().getStartTime());
                System.out.println(it.getValue().getEndTime());
            }
        }
    }

}


