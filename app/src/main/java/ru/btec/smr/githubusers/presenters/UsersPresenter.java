package ru.btec.smr.githubusers.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.realm.Realm;
import ru.btec.smr.githubusers.dbrealm.DbRealm;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.rest.NetApiClient;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;


@InjectViewState
public class UsersPresenter extends MvpPresenter<UsersView> implements Subscriber<List<GithubUser>> {
    private final String TAG = "UsersPresenter";
    private static final int pageSize = 30;
    DbRealm dbRealm = new DbRealm()   ;
    public final Realm realm;


    public UsersPresenter() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getData(0, pageSize);
    }

    @Override
    public void attachView(UsersView view) {
        super.attachView(view);
    }



    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<GithubUser> githubUsers) {
        dbRealm.saveToBase(githubUsers, realm);
       // DbRealm.addItemAsync(githubUsers, realm);
        getViewState().setUserList(githubUsers);
        Log.e(TAG, "size = " + githubUsers.size());
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
        dbRealm.selectAllRealm(realm);
        Log.e(TAG, " selectAllRealm");
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



}
