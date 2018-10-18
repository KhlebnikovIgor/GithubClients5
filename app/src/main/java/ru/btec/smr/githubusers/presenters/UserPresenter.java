package ru.btec.smr.githubusers.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.rest.NetApiClient;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView>
        implements Observer<GithubUser> {

    @Override
    public void attachView(UserView view) {
        super.attachView(view);
        loadDate();
    }

    private void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getUser("rumpilstilstkin")
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        //nope
    }

    @Override
    public void onNext(GithubUser githubUser) {
        getViewState().setImage(githubUser.getAvatar());
        getViewState().setName(githubUser.getLogin());
    }

    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }
}
