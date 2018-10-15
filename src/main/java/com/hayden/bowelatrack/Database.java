package com.hayden.bowelatrack;

import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Database {

    private int _id;
    private String _date;

    public Database() {

    }

    public Database(LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = df.format(date);
        Log.d("Hayden","Formatted date: " + formattedDate);
        this._date = formattedDate;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_date(LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = df.format(date);
        this._date = formattedDate;
    }

    public int get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }
}
