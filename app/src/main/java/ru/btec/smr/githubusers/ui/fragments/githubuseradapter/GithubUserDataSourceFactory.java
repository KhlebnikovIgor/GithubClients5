package ru.btec.smr.githubusers.ui.fragments.githubuseradapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import ru.btec.smr.githubusers.model.GithubUser;



public class GithubUserDataSourceFactory extends DataSource.Factory<Long, GithubUser> {

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<GithubUserDataSource> usersDataSourceLiveData = new MutableLiveData<>();

    public GithubUserDataSourceFactory(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, GithubUser> create() {
        GithubUserDataSource usersDataSource = new GithubUserDataSource(compositeDisposable);
        usersDataSourceLiveData.postValue(usersDataSource);
        return usersDataSource;
    }

    @NonNull
    public MutableLiveData<GithubUserDataSource> getUsersDataSourceLiveData() {
        return usersDataSourceLiveData;
    }
}