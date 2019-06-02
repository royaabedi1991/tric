
package com.a3rick.a3rick.models.ApiModels.OTP;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ApiResult {
    public Boolean getSuccessful() {
        return IsSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        IsSuccessful = successful;
    }

    @SerializedName("IsSuccessful")
    private Boolean IsSuccessful;
    @SerializedName("Message")
    private String Message;
    @SerializedName("Result_")
    private Long Result;

    public Boolean getIsSuccessful() {
        return IsSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        IsSuccessful = isSuccessful;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Long getResult() {
        return Result;
    }

    public void setResult(Long result) {
        Result = result;
    }

}
