package com.databaseWorker;

public class Doctor {
    public String FIO;
    public String telephone;
    public int cabinet;

    public Doctor(String fio, String tel_num, int cabinetIn){
        this.FIO = fio;
        this.telephone = tel_num;
        this.cabinet = cabinetIn;
    }

}
