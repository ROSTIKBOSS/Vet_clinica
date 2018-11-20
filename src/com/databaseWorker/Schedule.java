package com.databaseWorker;

public class Schedule {
    private enum D {
        MON,
        TUE,
        WED,
        THU,
        FRI,
        SAT,
        SUN
    }
    private D d;
    private int startHr;
    private int startMn;
    private int startSec;
    private int endHr;
    private int endMn;
    private int endSec;
    private Schedule()
    {

    }
    public Schedule(String day)
    {
        day.toLowerCase();
        switch (day)
        {
            case "monday":
                d = d.MON;
                break;
            case "tuesday":
                d = d.TUE;
                break;
            case "wednesday":
                d = d.WED;
                break;
            case "thursday":
                d = d.THU;
                break;
            case "friday":
                d = d.FRI;
                break;
            case "saturday":
                d = d.SAT;
                break;
            case "sunday":
                d = d.SUN;
                break;
            default:
                d = d.MON;
                break;
        }
        startHr = 9;
        endHr = 17;
        startMn = startSec = endMn = endSec = 0;
    }
    public Schedule(String day, int stHr,int stMn, int stSec,int enHr, int enMn, int enSec)
    {
        day.toLowerCase();
        switch (day)
        {
            case "monday":
                d = d.MON;
                break;
            case "tuesday":
                d = d.TUE;
                break;
            case "wednesday":
                d = d.WED;
                break;
            case "thursday":
                d = d.THU;
                break;
            case "friday":
                d = d.FRI;
                break;
            case "saturday":
                d = d.SAT;
                break;
            case "sunday":
                d = d.SUN;
                break;
            default:
                d = d.MON;
                break;
        }
        startHr = stHr;
        startMn = stMn;
        startSec = stSec;
        endHr = enHr;
        endMn = enMn;
        endSec = enSec;
    }
    public Schedule(Schedule sch)
    {
        switch (sch.d)
        {
            case MON:
                d = d.MON;
            case TUE:
                d = d.TUE;
            case WED:
                d = d.WED;
            case THU:
                d = d.THU;
            case FRI:
                d = d.FRI;
            case SAT:
                d = d.SAT;
            case SUN:
                d = d.SUN;
        }
        startHr = sch.startHr;
        startMn = sch.startMn;
        startSec = sch.startSec;
        endHr = sch.endHr;
        endMn = sch.endMn;
        endSec = sch.startSec;
    }
    public String getDay()
    {
        switch (d)
        {
            case MON:
                return "Monday";
            case TUE:
                return "Tuesday";
            case WED:
                return "Wednesday";
            case THU:
                return "Thursday";
            case FRI:
                return "Friday";
            case SAT:
                return "Saturday";
            case SUN:
                return "Sunday";
        }
        return "";
    }
    public String getStartTime()
    {
        String ret = "";
        if(startHr < 10) ret += "0";
        ret+=startHr + ":";
        if(startMn < 10) ret += "0";
        ret+=startMn + ":";
        if(startSec < 10) ret += "0";
        ret+=startSec;
        return ret;
    }
    public String getEndTime() {
        String ret = "";
        if (endHr < 10) ret += "0";
        ret += endHr + ":";
        if (endMn < 10) ret += "0";
        ret += endMn + ":";
        if (endSec < 10) ret += "0";
        ret += endSec;
        return ret;
    }
}
