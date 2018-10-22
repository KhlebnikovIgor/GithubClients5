package ru.btec.smr.githubusers.dbrealm;

import java.util.List;

import ru.btec.smr.githubusers.model.GithubUser;

public interface OnSaveToBaseListener {
    void saveToBase(List<GithubUser> githubUsers);

}
