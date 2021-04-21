package com.example.tourgk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tour extends Abstract<com.example.tourgk.model.Tour> {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tourID")
    @Expose
    private String tourID;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("duration")
    @Expose
    private Integer duration;

    @SerializedName("maxGroupSize")
    @Expose
    private Integer maxGroupSize;

    @SerializedName("currentGroupSize")
    @Expose
    private Integer currentGroupSize;

    @SerializedName("price")
    @Expose
    private Long price;

    @SerializedName("employeeID")
    @Expose
    private Long employeeID;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("images")
    @Expose
    private String images;

    @SerializedName("imageCover")
    @Expose
    private String imageCover;

    @SerializedName("base64")
    @Expose
    private String base64;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(Integer maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public Integer getCurrentGroupSize() {
        return currentGroupSize;
    }

    public void setCurrentGroupSize(Integer currentGroupSize) {
        this.currentGroupSize = currentGroupSize;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
