package com.a3rick.a3rick.models.ApiModels.Teepeto.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllTag {

    @SerializedName("TagId")
    @Expose
    private Integer tagId;
    @SerializedName("Title")
    @Expose
    private String title;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}