package com.a3rick.a3rick.webService.Trick;

import com.a3rick.a3rick.models.models.Trick.favorites.AddFavoriteContentResult;
import com.a3rick.a3rick.models.models.Trick.categories.GetAllCategoryResult;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.GetContentResult;
import com.a3rick.a3rick.models.models.Trick.favorites.GetLikeStateResult;
import com.a3rick.a3rick.models.models.Trick.favorites.GetViewCountResult;
import com.a3rick.a3rick.models.models.Trick.content_with_contentId.GetContentWithIdResult;
import com.a3rick.a3rick.models.models.Trick.favorites.DeleteFavoriteContentResult;
import com.a3rick.a3rick.models.models.Trick.favorites.GetLikeDisLikeResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FileApi {

    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetOrderedContentsWithCategoryId")
    Call<GetContentResult> getContent(
            @Query("CategoryId") int CategoryId,
            @Query("PageNumber") int PageNumber,
            @Query("RowCount") int RowCount,
            @Query("QueryType") String QueryType);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/LikeOrDisLike")
    Call<GetLikeDisLikeResult> getLikeDisLike
            (@Query("ContentId") int ContentId);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Category/GetAll")
    Call<GetAllCategoryResult> getAllCategory(@Query("parentId") int parentId);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetLikeStatus")
    Call<GetLikeStateResult> getLikeState(
            @Query("ContentId") int ContentId);

    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetViewCount")
    Call<GetViewCountResult> getViewCount(
            @Query("ContentId") int ContentId);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Content/GetContentWithContentId")
    Call<GetContentWithIdResult> getContentWithId(
            @Query("ContentId") int ContentId);

    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @POST("Favorits/AddContentToFavorits")
    Call<AddFavoriteContentResult> addFavorite(
            @Query("ContentId") int ContentId);


    @Headers("Token:0de0a7b0-5a86-447e-857b-17446c097de2")
    @GET("Favorits/Delete?favoriteId")
    Call<DeleteFavoriteContentResult> deleteFavorite(
            @Query("favoriteId") int favoriteId);

}
