package ru.btec.smr.githubusers.ui.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.dbrealm.DbGithubUser;
import ru.btec.smr.githubusers.dbrealm.DbGithubUserList;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.presenters.UsersPresenter;
import ru.btec.smr.githubusers.presenters.UsersView;
import ru.btec.smr.githubusers.ui.fragments.githubuseradapter.GithubUserAdapter;

public class GithubUserListFragment extends MvpAppCompatFragment implements UsersView {
    @InjectPresenter
    UsersPresenter usersPresenter;

    private RecyclerView recyclerView;
    private GithubUserAdapter adapter;
    //private Realm realm = Realm.getDefaultInstance();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        usersPresenter.realm.close();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_githubuser_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.githubuser_list);
        setUpRecyclerView();
        //setContentView(R.layout.activity_recyclerview);

        //recyclerView = findViewById(R.id.recycler_view);
       // setUpRecyclerView();
//        adapter = new GithubUserAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
//        usersPresenter.userList.observe(this, adapter::submitList);
//        usersPresenter.getNetworkState().observe(this, adapter::setNetworkState);

        return root;
    }

    private void setUpRecyclerView() {
       // OrderedRealmCollection<DbGithubUser> data = usersPresenter.realm.where(DbGithubUser.class).findAll();
        adapter = new GithubUserAdapter( usersPresenter.realm.where(DbGithubUser.class).findAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));



//        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
//        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
//        touchHelper.attachToRecyclerView(recyclerView);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setUserList(List<GithubUser> userList) {
        adapter.notifyDataSetChanged();
        Log.e("GithubUserListFragment ", "size = " + userList.size());
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
