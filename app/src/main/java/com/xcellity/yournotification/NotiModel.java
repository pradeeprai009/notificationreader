package com.xcellity.yournotification;

public class NotiModel {
    String id;
    String title;
    String message;
    String packages;
    String date;

    public NotiModel(String id,String title, String message, String packages, String date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.packages = packages;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
