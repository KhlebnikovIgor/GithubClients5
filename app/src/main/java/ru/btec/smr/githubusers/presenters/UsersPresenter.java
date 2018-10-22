package ru.btec.smr.githubusers.presenters;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.rest.NetApiClient;
import ru.btec.smr.githubusers.ui.fragments.githubuseradapter.GithubUserDataSource;
import ru.btec.smr.githubusers.ui.fragments.githubuseradapter.GithubUserDataSourceFactory;
import ru.btec.smr.githubusers.ui.fragments.githubuseradapter.NetworkState;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;


@InjectViewState
public class UsersPresenter extends MvpPresenter<UsersView> implements Subscriber<List<GithubUser>> {
    private final String TAG = "UsersPresenter";
    public LiveData<PagedList<GithubUser>> userList;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final int pageSize = 30;
    private GithubUserDataSourceFactory usersDataSourceFactory;

    public UsersPresenter() {
        usersDataSourceFactory = new GithubUserDataSourceFactory(compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build();
        userList = new LivePagedListBuilder<>(usersDataSourceFactory, config).build();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    @Override
    public void attachView(UsersView view) {
        super.attachView(view);
    }

    public void retry() {
        usersDataSourceFactory.getUsersDataSourceLiveData().getValue().retry();
    }

    public void refresh() {
        usersDataSourceFactory.getUsersDataSourceLiveData().getValue().invalidate();
    }

    public LiveData<NetworkState> getNetworkState() {
        return Transformations.switchMap(usersDataSourceFactory.getUsersDataSourceLiveData(), GithubUserDataSource::getNetworkState);
    }

    public LiveData<NetworkState> getRefreshState() {
        return Transformations.switchMap(usersDataSourceFactory.getUsersDataSourceLiveData(), GithubUserDataSource::getInitialLoad);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<GithubUser> githubUsers) {
        //getViewState().setUserList(githubUsers);

        Log.e(TAG, "size = " + githubUsers.size());
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }

    @Override
    public void onError(Throwable t) {
        getViewState().showError(t);
        getViewState().finishLoad();
    }



    public void getData(long since, int per_page) {
        getViewState().startLoad();

        NetApiClient.getInstance().getUsers(since, per_page).subscribe(this);
    }


    public void saveIntoCashe(){
       // Single<Bundle> singleSaveAllRealm = Single.create((SingleOnSubscribe<Bundle> emitter));
    }

}
