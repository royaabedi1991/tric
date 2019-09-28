package com.a3rick.a3rick.models.models.Trick.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("SubscriptionDateSh")
    @Expose
    private String subscriptionDateSh;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("ProfileImageUrl")
    @Expose
    private String profileImageUrl;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSubscriptionDateSh() {
        return subscriptionDateSh;
    }

    public void setSubscriptionDateSh(String subscriptionDateSh) {
        this.subscriptionDateSh = subscriptionDateSh;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}