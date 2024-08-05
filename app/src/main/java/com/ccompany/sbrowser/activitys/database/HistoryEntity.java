package com.ccompany.sbrowser.activitys.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class HistoryEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    String historyURL;

    String date;

    String time;

    public HistoryEntity(int id, String historyURL, String date, String time) {
        this.id = id;
        this.historyURL = historyURL;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHistoryURL() {
        return historyURL;
    }

    public void setHistoryURL(String historyURL) {
        this.historyURL = historyURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
