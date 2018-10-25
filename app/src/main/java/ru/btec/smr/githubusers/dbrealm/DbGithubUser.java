package ru.btec.smr.githubusers.dbrealm;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class DbGithubUser extends RealmObject {
   // public static final String FIELD_ID = "id";
    @PrimaryKey
    private long id;
    private String login;
    @SerializedName("avatar_url")
    private String avatar;

//    static void create(Realm realm) {
//        DbGithubUserList parent = realm.where(DbGithubUserList.class).findFirst();
//        RealmList<DbGithubUser> items = parent.getItemList();
//        DbGithubUser counter = realm.createObject(DbGithubUser.class, increment());
//
//            items.add(counter);
//    }

    public DbGithubUser() {
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
