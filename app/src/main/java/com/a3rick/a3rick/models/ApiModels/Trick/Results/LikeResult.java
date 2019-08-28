
package com.a3rick.a3rick.models.ApiModels.Trick.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeResult {

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