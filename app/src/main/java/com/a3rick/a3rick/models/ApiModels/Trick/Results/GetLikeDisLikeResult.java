package com.a3rick.a3rick.models.ApiModels.Trick.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLikeDisLikeResult {

@SerializedName("IsSuccessful")
@Expose
private Boolean isSuccessful;
@SerializedName("Message")
@Expose
private String message;
@SerializedName("LikeResult ")
@Expose
private LikeResult likeResult;

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

public LikeResult getLikeResult() {
return likeResult;
}

public void setLikeResult(LikeResult likeResult) {
this.likeResult = likeResult;
}

}