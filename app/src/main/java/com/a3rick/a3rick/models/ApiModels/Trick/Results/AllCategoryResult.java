package com.a3rick.a3rick.models.ApiModels.Trick.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCategoryResult {

    @SerializedName("ParentId")
    @Expose
    private Object parentId;
    @SerializedName("CategoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("HasChild")
    @Expose
    private Boolean hasChild;
    @SerializedName("BannerImageFileAddress")
    @Expose
    private String bannerImageFileAddress;
    @SerializedName("ThumbnailImageFileAddress")
    @Expose
    private String thumbnailImageFileAddress;

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public String getBannerImageFileAddress() {
        return bannerImageFileAddress;
    }

    public void setBannerImageFileAddress(String bannerImageFileAddress) {
        this.bannerImageFileAddress = bannerImageFileAddress;
    }

    public String getThumbnailImageFileAddress() {
        return thumbnailImageFileAddress;
    }

    public void setThumbnailImageFileAddress(String thumbnailImageFileAddress) {
        this.thumbnailImageFileAddress = thumbnailImageFileAddress;
    }

}