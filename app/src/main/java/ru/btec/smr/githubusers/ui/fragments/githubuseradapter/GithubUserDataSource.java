package ru.btec.smr.githubusers.ui.fragments.githubuseradapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.rest.NetApiClient;
import timber.log.Timber;

public class GithubUserDataSource extends ItemKeyedDataSource<Long, GithubUser> {

    private NetApiClient githubService;

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

    private MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();

    private Completable retryCompletable;

    GithubUserDataSource(CompositeDisposable compositeDisposable) {
        this.githubService = NetApiClient.getInstance();
        this.compositeDisposable = compositeDisposable;
    }

    public void retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, throwable -> Timber.e(throwable.getMessage())));
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<GithubUser> callback) {
        networkState.postValue(NetworkState.LOADING);
        initialLoad.postValue(NetworkState.LOADING);
        compositeDisposable.add(githubService.getUsers(0, params.requestedLoadSize).subscribe(users -> {
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    initialLoad.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                    Log.e("loadInitial", Integer.toString(users.size()));
                },
                throwable -> {
                    setRetry(() -> loadInitial(params, callback));
                    NetworkState error = NetworkState.error(throwable.getMessage());
                    networkState.postValue(error);
                    initialLoad.postValue(error);
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GithubUser> callback) {
        networkState.postValue(NetworkState.LOADING);
        compositeDisposable.add(githubService.getUsers(params.key, params.requestedLoadSize).subscribe(users -> {
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                    Log.e("loadAfter", Integer.toString(users.size()));
                },
                throwable -> {
                    setRetry(() -> loadAfter(params, callback));
                    networkState.postValue(NetworkState.error(throwable.getMessage()));
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GithubUser> callback) {
    }


    @NonNull
    @Override
    public Long getKey(@NonNull GithubUser item) {
        return item.getId();
    }

    @NonNull
    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    @NonNull
    public MutableLiveData<NetworkState> getInitialLoad() {
        return initialLoad;
    }

    private void setRetry(final Action action) {
        if (action == null) {
            this.retryCompletable = null;
        } else {
            this.retryCompletable = Completable.fromAction(action);
        }
    }
}