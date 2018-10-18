package ru.btec.smr.githubusers.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.rest.NetApiClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;


@InjectViewState
public class UsersPresenter extends MvpPresenter<UsersView> implements Subscriber<List<GithubUser>> {
    private final String TAG = "UsersPresenter";

    @Override
    public void attachView(UsersView view) {
        super.attachView(view);
        loadData();
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<GithubUser> githubUsers) {
        getViewState().setUserList(githubUsers);

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

    private void loadData(){
        getViewState().startLoad();

        NetApiClient.getInstance().getUsers().subscribe(this);
    }
}
