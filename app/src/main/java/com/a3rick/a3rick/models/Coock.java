package com.a3rick.a3rick.models;

import android.widget.TextView;

public class Coock {
    private int resource;

    public Coock() {

    }

    public int getResorce() {
        return resource;
    }

    public void setResorce(int resorce) {
        this.resource = resorce;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    private TextView textView;

    public Coock(int resorce, TextView textView) {
        this.resource = resorce;
        this.textView = textView;
    }
}
