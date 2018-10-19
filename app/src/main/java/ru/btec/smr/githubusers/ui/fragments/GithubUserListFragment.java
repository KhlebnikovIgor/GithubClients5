package ru.btec.smr.githubusers.ui.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.presenters.UsersPresenter;
import ru.btec.smr.githubusers.presenters.UsersView;

import java.util.LinkedList;
import java.util.List;

public class GithubUserListFragment extends MvpAppCompatFragment implements UsersView {
    @InjectPresenter
    UsersPresenter usersPresenter;

    List<GithubUser> githubUsers = new LinkedList<>();
    RecyclerView recyclerView;
    GithubUserAdapter adapter;


    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_githubuser_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.workout_list);
        adapter = new GithubUserAdapter(githubUsers, this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserList(List<GithubUser> userList) {
        adapter.setData(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void finishLoad() {

    }
}
