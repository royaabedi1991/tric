
package com.a3rick.a3rick.models.models.Trick.content_with_tagId;


import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetContentsWithTagIdsResult {

    @SerializedName("IsSuccessful")
    @Expose
    private Boolean isSuccessful;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("Result")
    @Expose
    private List<Result> result = null;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}