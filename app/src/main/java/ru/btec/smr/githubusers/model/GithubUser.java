package ru.btec.smr.githubusers.model;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class GithubUser  {
    @PrimaryKey
    private long id;
    private String login;
    @SerializedName("avatar_url")
    private String avatar;


    public GithubUser() {
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    @Nullable
    public String getLogin() {
        return login;
    }

    @Nullable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
