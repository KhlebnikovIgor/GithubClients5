package ru.btec.smr.githubusers.rest;


import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.model.RepsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NetApiClient {

    private static final NetApiClient ourInstance = new NetApiClient();

    public static NetApiClient getInstance() {
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    public Observable<GithubUser> getUser(String user) {
        return netApi.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<GithubUser>> getUsers() {
        return netApi.getUsers(100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<RepsModel>> getReps() {
        return netApi.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
