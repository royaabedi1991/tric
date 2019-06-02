package com.a3rick.a3rick.models.ApiModels.OTP;

import com.google.gson.annotations.SerializedName;

public class SubRequest {
    @SerializedName("ServiceId")
    private int ServiceId;
    @SerializedName("TransactionId")
    private int TransactionId;
    @SerializedName("Pin")
    private int Pin;

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }

    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transaction) {
        TransactionId = transaction;
    }

    public int getPin() {
        return Pin;
    }

    public void setPin(int pin) {
        Pin = pin;
    }
}
