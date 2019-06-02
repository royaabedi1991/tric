package com.a3rick.a3rick.models.RecyclerViewModels;

public class Category {

    private int resource;
    private String title;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category() {
        this.resource = resource;
        this.title = title;
    }

    public int getRecource() {
        return resource;
    }
}
