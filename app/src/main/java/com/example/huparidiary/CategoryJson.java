
package com.example.huparidiary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryJson {


    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("catname")
    @Expose
    private String catname;
    @SerializedName("catimage")
    @Expose
    private String catimage;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatimage() {
        return catimage;
    }

    public void setCatimage(String catimage) {
        this.catimage = catimage;
    }

}

