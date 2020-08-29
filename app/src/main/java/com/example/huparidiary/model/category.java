package com.example.huparidiary.model;

public class category {
    String uid;
    String catName;
    String catImage;

    public category() {     // default constructor
    }


    public category(String uid, String catName, String catImage) {
        this.uid = uid;
        this.catName = catName;
        this.catImage = catImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }
}
