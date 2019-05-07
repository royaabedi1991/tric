package com.a3rick.a3rick.models;

import com.google.gson.annotations.SerializedName;

public class GetSubResult {

    @SerializedName("IsSuccessful")
    private Boolean isSuccessful;
    @SerializedName("Message")
    private String message;
    @SerializedName("Result")
    private Boolean result;

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
