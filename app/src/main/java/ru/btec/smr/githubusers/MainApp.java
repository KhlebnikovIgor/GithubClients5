package ru.btec.smr.githubusers;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.btec.smr.githubusers.dbrealm.DbGithubUser;
import ru.btec.smr.githubusers.dbrealm.DbGithubUserList;

public class MainApp extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
//        Realm.init(this);
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .initialData(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.createObject(DbGithubUserList.class);
//                    }})
//                .build();
//        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
//        Realm.setDefaultConfiguration(realmConfig);

    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }
}
