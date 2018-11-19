package com.company;

import com.databaseWorker.DataBaseWorker;
import com.databaseWorker.ErrorEnum;
import com.databaseWorker.DoctorResults;
import com.databaseWorker.ClientResults;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        DataBaseWorker worker = new DataBaseWorker();
        worker.Connect();
        worker.InsertToDoctor(
                "ssdf",
                1,
                "12343");
    }

}


