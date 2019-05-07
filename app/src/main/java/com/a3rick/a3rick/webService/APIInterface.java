package com.a3rick.a3rick.webService;

import com.a3rick.a3rick.models.ApiResult;
import com.a3rick.a3rick.models.GetSubResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("Otp/SubscribeRequest")
    @FormUrlEncoded
    Call <ApiResult> registerUser(@Field("MobileNumber") String MobileNumber, @Field("ServiceId") int ServiceId, @Field("Channel") String Channel );

    @POST("Otp/SubscribeConfirm")
    @FormUrlEncoded

    Call <ApiResult> subscribeUser(@Field("ServiceId") String ServiceId, @Field("TransactionId") String TransactionId, @Field("Pin") String Pin );

    @POST("Otp/SubscribeConfirmViaCode")
    @FormUrlEncoded
    Call <ApiResult> subscribeUserViaCode (@Field("ServiceId") String ServiceId, @Field("TransactionId") String TransactionId, @Field("Pin") String Pin);


    @GET ("Subscription/GetSubscriptionState")
    Call <GetSubResult> getSubRequest (@Query("MobileNumber") String MobileNumber, @Query("ServiceId") int ServiceId  );

}

