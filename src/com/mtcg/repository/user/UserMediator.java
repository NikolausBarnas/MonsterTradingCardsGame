package com.mtcg.repository.user;

import com.google.gson.annotations.SerializedName;

public class UserMediator {
    @SerializedName("Name")
    private String name;
    @SerializedName("Bio")
    private String bio;
    @SerializedName("Image")
    private String image;

    public UserMediator (String name, String bio, String image) {
        this.name = name;
        this.bio = bio;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
