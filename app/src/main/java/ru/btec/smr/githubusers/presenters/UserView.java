package ru.btec.smr.githubusers.presenters;


import com.arellomobile.mvp.MvpView;


public interface UserView extends MvpView {
    void setName(String name);
    void setImage(String imageUrl);

    void showError(Throwable e);

    void startLoad();
    void finishLoad();
}
