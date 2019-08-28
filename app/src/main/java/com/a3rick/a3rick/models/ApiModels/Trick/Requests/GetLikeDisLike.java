package com.a3rick.a3rick.models.ApiModels.Trick.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLikeDisLike {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName(" contentId")
    @Expose
    private String contentId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}