package ru.btec.smr.githubusers.model;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class GithubUser {
    private String login;
    @SerializedName("avatar_url")
    private String avatar;
    private int id;

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    @Nullable
    public String getLogin() {
        return login;
    }

    @Nullable
    public int getId() {
        return id;
    }
}
