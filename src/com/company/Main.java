package com.company;

import com.databaseWorker.DataBaseWorker;
import com.databaseWorker.ErrorEnum;
import com.databaseWorker.DoctorResults;
import com.databaseWorker.ClientResults;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException
    {
        DataBaseWorker worker = new DataBaseWorker();
        ErrorEnum status = worker.Connect();
        if(status == ErrorEnum.Success){
            if(worker.CheckTableToEmpty("doctor"))
            {
                var doctors = worker.QuerySlectAllDcotors();
            }
            else
            {
                var insertStatus = worker.InsertToDoctor(
                        1,
                        "ssdf",
                        1,
                        "12343");

            }
            if(worker.CheckTableToEmpty("owner"))
            {
                var client = worker.QuerySlectAllClients();
            }
            else
            {
                var insertStatus = worker.InsertToOwner(
                        1,
                        "ssdf",
                        "sdfsdf",
                        "12343",
                        1);
            }
        }
    }
}
