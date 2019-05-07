package com.a3rick.a3rick.models;

import android.widget.TextView;

public class Fun {
    private int resource;
    private TextView textView;

    public Fun() {

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

    public Fun(int resource, TextView textView) {
        this.resource = resource;
        this.textView = textView;
    }
}
