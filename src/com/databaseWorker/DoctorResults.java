package com.databaseWorker;

public class DoctorResults {
    public static String FIO;
    public static String telephone;
    public static int cabinet;

    public DoctorResults(String fio, String tel_num, int cabinetIn){
        this.FIO = fio;
        this.telephone = tel_num;
        this.cabinet = cabinetIn;
    }

}
