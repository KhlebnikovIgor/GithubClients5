package ru.btec.smr.githubusers.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.interfaces.OnGithubuserListItemSelectedListener;

public class GithubuserReposDetailFragment extends Fragment {
    OnGithubuserListItemSelectedListener callbackActivity;
    private TextView repos_name;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (OnGithubuserListItemSelectedListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static GithubuserReposDetailFragment newInstance(int Index) {
        GithubuserReposDetailFragment fragment = new GithubuserReposDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_githubuser_repos, container, false);
        initUI(root); //Инициализация пользовательского интерфейса, связывание xml с кодом.
        return root;
    }

    private void initUI(View root) {
        repos_name = (TextView) root.findViewById(R.id.repos_name);
        repos_name.setText("Здесь должен быть список репозитариев!!!\nПока не реализованно!!!");
        //callbackActivity.refreshList();

    }
}

