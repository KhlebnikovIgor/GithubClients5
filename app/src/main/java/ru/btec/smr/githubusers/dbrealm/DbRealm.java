package ru.btec.smr.githubusers.dbrealm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.btec.smr.githubusers.model.GithubUser;


public class DbRealm {
    private static final String EXT_COUNT = "ext_count";
    private static final String TAG = "DbRealm";
    //private  Realm realm = Realm.getDefaultInstance();

    public void addItemAsync(List<GithubUser> githubUsers, Realm realm) {
        realm.executeTransactionAsync(realm1 -> {

            for (GithubUser curItem : githubUsers) {
                try {
                    realm1.beginTransaction();
                    DbGithubUser realmModel = realm1.createObject(DbGithubUser.class);
                    realmModel.setId(curItem.getId());
                    realmModel.setLogin(curItem.getLogin());
                    realmModel.setAvatar(curItem.getAvatar());
                    realm1.commitTransaction();
                } catch (Exception e) {
                    realm1.cancelTransaction();
                }
            }
        });
    }

    public void saveToBase(List<GithubUser> githubUsers, Realm realm) {
        Single<Bundle> singleSaveAllRealm = Single.create((SingleOnSubscribe<Bundle>) emitter -> {
            try {
//                Realm  realm = Realm.getDefaultInstance();
//                Date first = new Date();

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
                long count = realm.where(DbGithubUser.class).count();
                Bundle bundle = new Bundle();
                bundle.putInt(EXT_COUNT, (int) count);
                //  bundle.putLong(EXT_TIME, second.getTime() - first.getTime());
                emitter.onSuccess(bundle);
                realm.close();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        singleSaveAllRealm.subscribeWith(CreateObserver());
        Log.e(TAG, "saveToBase");
    }

    public void selectAllRealm(Realm realm) {
        Single<Bundle> singleSelectAllRealm = Single.create((SingleOnSubscribe<Bundle>) emitter -> {
            try {
                //realm = Realm.getDefaultInstance();
                Date first = new Date();
                RealmResults<DbGithubUser> tempList = realm.where(DbGithubUser.class).findAll();
                Date second = new Date();
                Bundle bundle = new Bundle();
                bundle.putInt(EXT_COUNT, tempList.size());
                Log.e(TAG, "selectAllRealm количество = " + tempList.size());
                //bundle.putLong(EXT_TIME, second.getTime() - first.getTime());
                emitter.onSuccess(bundle);
                realm.close();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        singleSelectAllRealm.subscribeWith(CreateObserver());
    }


    private DisposableSingleObserver<Bundle> CreateObserver() {
        return new DisposableSingleObserver<Bundle>() {

            @Override
            protected void onStart() {
                super.onStart();
//                progressBar.setVisibility(View.VISIBLE);
//                mInfoTextView.setText("");
            }

            @Override
            public void onSuccess(@NonNull Bundle bundle) {
//                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "количество = " + bundle.getInt(EXT_COUNT));
//                mInfoTextView.append("количество = " + bundle.getInt(EXT_COUNT) +
//                        "\n милисекунд = " + bundle.getLong(EXT_TIME));
            }

            @Override
            public void onError(@NonNull Throwable e) {
//                progressBar.setVisibility(View.GONE);
//                mInfoTextView.setText("ошибка БД: " + e.getMessage());
            }
        };
    }


}
