package com.ccompany.sbrowser.activitys.pojoclass;

public class PojoHistory {

    int id;
    String url;
    String date;
    String time;

    public PojoHistory(int id, String url, String date, String time) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
