package com.a3rick.a3rick.models.models.Trick.favorites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("IsLiked")
    @Expose
    private Boolean isLiked;
    @SerializedName("TotalLike")
    @Expose
    private Integer totalLike;

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

}