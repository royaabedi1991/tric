package com.a3rick.a3rick.models.ApiModels.OTP;

import com.google.gson.annotations.SerializedName;

public class User {
    public int getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    @SerializedName("MobileNumber")
    private int MobileNumber;
    @SerializedName("ServiceId")
    private int ServiceId;
    @SerializedName("Channel")
    private String Channel;
}
