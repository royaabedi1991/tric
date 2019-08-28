package com.a3rick.a3rick.models.ApiModels.Trick.Results;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllCategoryResult {

    @SerializedName("IsSuccessful")
    @Expose
    private Boolean isSuccessful;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("AllCategoryResult")
    @Expose
    private List<AllCategoryResult> allCategoryResult = null;

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<AllCategoryResult> getAllCategoryResult() {
        return allCategoryResult;
    }

    public void setAllCategoryResult(List<AllCategoryResult> allCategoryResult) {
        this.allCategoryResult = allCategoryResult;
    }

}