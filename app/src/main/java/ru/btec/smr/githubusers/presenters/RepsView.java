package ru.btec.smr.githubusers.presenters;


import com.arellomobile.mvp.MvpView;


public interface RepsView extends MvpView {

    void showError(Throwable e);

    void startLoad();
    void finishLoad();
}
