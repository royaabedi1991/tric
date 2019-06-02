package com.a3rick.a3rick.webService.Teepeto;

import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.GetContentResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FileApi {

    @Headers("Token: 007b428d-b807-4ccd-a3a8-afdcc0f18d0b")
    @GET("Api/Content/GetFilterOrderedContents")
    Call<GetContentResult> getContent(/*@Query("Token") String Token,*/
                                             @Query("CategoryId") int CategoryId,
                                             @Query("PageNumber") int PageNumber,
                                             @Query("RowCount") int RowCount,
                                             @Query("QueryType") String QueryType);


}
