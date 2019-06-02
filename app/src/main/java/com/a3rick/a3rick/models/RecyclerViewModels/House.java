package com.a3rick.a3rick.models.RecyclerViewModels;

import android.widget.TextView;

public class House {

    private int resource;
    private TextView textView;

    public House() {

    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public House(int resource, TextView textView) {
        this.resource = resource;
        this.textView = textView;


    }
}
