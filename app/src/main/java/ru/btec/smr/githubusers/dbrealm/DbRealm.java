package ru.btec.smr.githubusers.dbrealm;

import android.os.Bundle;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import ru.btec.smr.githubusers.model.GithubUser;

public class DbRealm implements OnSaveToBaseListener {
    private Realm realm;

    private static final DbRealm ourInstance = new DbRealm();

    public static DbRealm getInstance() {
        return ourInstance;
    }

    private DbRealm() {
    }

    @Override
    public void saveToBase(List<GithubUser> githubUsers) {
        Single<Bundle> singleSaveAllRealm = Single.create((SingleOnSubscribe<Bundle>) emitter -> {
            try {
                realm = Realm.getDefaultInstance();
                for (GithubUser curItem : githubUsers) {
                    try {
                        realm.beginTransaction();
                        DbGithubUser realmModel = realm.createObject(DbGithubUser.class);
                        realmModel.setId(curItem.getId());
                        realmModel.setLogin(curItem.getLogin());
                        realmModel.setAvatar(curItem.getAvatar());
                        realm.commitTransaction();
                    } catch (Exception e) {
                        realm.cancelTransaction();
                        emitter.onError(e);
                    }
                }
                realm.close();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        singleSaveAllRealm.subscribeWith(CreateObserver());
    }


}
