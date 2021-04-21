package com.example.tourgk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Abstract<T> {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("createdDate")
    @Expose
    private Timestamp createdDate;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("ids")
    @Expose
    private long[] ids;

    @SerializedName("listResult")
    @Expose
    private List<T> listResult = new ArrayList<>();

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("limit")
    @Expose
    private Integer limit;

    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;

    @SerializedName("totalItem")
    @Expose
    private Integer totalItem;

    @SerializedName("sortName")
    @Expose
    private String sortName;

    @SerializedName("sortBy")
    @Expose
    private String sortBy;

    @SerializedName("alert")
    @Expose
    private String alert;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("type")
    @Expose
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
