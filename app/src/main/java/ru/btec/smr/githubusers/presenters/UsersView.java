package ru.btec.smr.githubusers.presenters;


import com.arellomobile.mvp.MvpView;
import ru.btec.smr.githubusers.model.GithubUser;

import java.util.List;


public interface UsersView extends MvpView {
   // void setUserList(List<GithubUser> userList);
    void showError(Throwable e);

    void startLoad();
    void finishLoad();
}
