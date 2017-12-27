package com.example.kevin.assignment2;
import java.util.Date;

/**
 * Created by Kevin on 11/4/17.
 */

public class ExpenseLogEntryData {
    private String description;
    private String note;
    private String time;

    public ExpenseLogEntryData(String description, String note) {
        this.description=description;
        this.note=note;
        Date d=new Date();
        this.time=d.toString();
    }

    protected String getDescription() {
        return description;

    }

    protected String getNote() {
        return note;

    }
    protected String getTime() {
        return time;

    }

    private void setDescription(String d) {
        this.description=d;

    }

    private void setNote(String n) {
        this.note=n;
    }

    protected void setTime() {
        Date d=new Date();
        this.time=d.toString();

    }
    protected void updateTime(String s) {
        this.time=s;

    }

}
