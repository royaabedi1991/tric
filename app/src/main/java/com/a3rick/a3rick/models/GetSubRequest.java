package com.a3rick.a3rick.models;

import com.google.gson.annotations.SerializedName;

public class GetSubRequest {
    @SerializedName("MobileNumber")
    private int mobileNumber;
    @SerializedName("ServiceId")
    private int serviceId;

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
