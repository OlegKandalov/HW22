package com.example.homewithmongo.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Alarm {

    private int id;
    private String time;
    private List<Days> days;
    boolean isOn;

    public Alarm() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public List<Days> getDays() {
        return days;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }
}
