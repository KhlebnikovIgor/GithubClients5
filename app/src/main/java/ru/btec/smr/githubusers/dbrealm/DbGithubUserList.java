package ru.btec.smr.githubusers.dbrealm;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DbGithubUserList extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<DbGithubUser> itemList;

    public RealmList<DbGithubUser>
    getItemList() {
        return itemList;
    }
}
