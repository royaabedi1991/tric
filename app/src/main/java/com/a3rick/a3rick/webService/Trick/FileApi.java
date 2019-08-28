package com.a3rick.a3rick.webService.Trick;

import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetAllCategoryResult;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetContentResult;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetLikeDisLikeResult;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetLikeStateResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FileApi {

    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetFilterOrderedContents")
    Call<GetContentResult> getContent(
            @Query("CategoryId") int CategoryId,
            @Query("PageNumber") int PageNumber,
            @Query("RowCount") int RowCount,
            @Query("QueryType") String QueryType);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/LikeOrDisLike")
    Call<GetLikeDisLikeResult> getLike(@Query("Token") String Token,
                                       @Query("ContentId") int ContentId);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Category/GetAll")
    Call<GetAllCategoryResult> getAllCategory(@Query("parentId") int parentId);



    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetLikeStatus")
    Call<GetLikeStateResult> getLikeState(@Query("Token") String Token,
                                          @Query("ContentId") int ContentId);

}
