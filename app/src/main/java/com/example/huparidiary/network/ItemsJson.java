
package com.example.huparidiary.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemsJson {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("ratings")
    @Expose
    private String ratings;
    @SerializedName("ranks")
    @Expose
    private String ranks;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image")
    @Expose
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
