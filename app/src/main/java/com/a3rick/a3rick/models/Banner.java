package com.a3rick.a3rick.models;

public class Banner {

private int recource;
//R.drawable.banner
    private String title;

    public Banner() {

    }

    public int getRecource() {
        return recource;
    }

    public void setRecource(int recource) {
        this.recource = recource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Banner(int recource, String title) {

        this.recource = recource;
        this.title = title;
    }
}
