package ru.btec.smr.githubusers.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.interfaces.OnGithubuserListItemSelectedListener;
import ru.btec.smr.githubusers.ui.fragments.GithubUserListFragment;
import ru.btec.smr.githubusers.ui.fragments.GithubuserReposDetailFragment;

public class MainActivity extends AppCompatActivity implements OnGithubuserListItemSelectedListener {
    FragmentManager fm;
    GithubUserListFragment listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        listFragment = new GithubUserListFragment();


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction().replace(R.id.container, listFragment).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GithubuserReposDetailFragment detailFragment = GithubuserReposDetailFragment.newInstance(0);
            fm.beginTransaction().replace(R.id.list_container, listFragment).commit();
            fm.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public void onWorkoutListItemSelected(int position) {
        GithubuserReposDetailFragment detailFragment = GithubuserReposDetailFragment.newInstance(position);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction().replace(R.id.container, detailFragment).addToBackStack(null).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fm.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public void refreshList() {
//        listFragment.refreshList();
    }
}
