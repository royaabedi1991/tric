package com.a3rick.a3rick.models.ApiModels.Trick.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetContent {

    @SerializedName("CategoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("PageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("RowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("QueryType")
    @Expose
    private String queryType;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
