package ru.btec.smr.githubusers.ui.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.interfaces.OnGithubuserListItemSelectedListener;
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
    OnGithubuserListItemSelectedListener callbackActivity;

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (OnGithubuserListItemSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_githubuser_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.workout_list);
        adapter = new GithubUserAdapter(githubUsers);//
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


    private class GithubUserAdapter extends RecyclerView.Adapter<GithubUsersHolder> {
        List<GithubUser> githubUsers;

        public GithubUserAdapter(List<GithubUser> githubUsers) {
            this.githubUsers = githubUsers;
            notifyDataSetChanged();
        }

        @Override
        public GithubUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.githubuser_list_item, parent, false);

            return new GithubUsersHolder(listItemView);
        }

        @Override
        public void onBindViewHolder(final GithubUsersHolder holder, int position) {
            holder.titleTextView.setText(Html.fromHtml("<font color=#639EFD>Id: </font>" + githubUsers.get(position).getId() + "<br><font color=#639EFD>Login: </font>" + githubUsers.get(position).getLogin()));
            Glide.with(holder.listItemCardView)
                    .load(githubUsers.get(position).getAvatar())
                    .into(holder.imageView);
            holder.listItemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbackActivity.onWorkoutListItemSelected(holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return githubUsers.size();
        }

        public void setData(List<GithubUser> userList) {
            this.githubUsers = userList;
        }
    }

    private class GithubUsersHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        CardView listItemCardView;

        public GithubUsersHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.item_title);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
            listItemCardView = (CardView) itemView.findViewById(R.id.list_item_card_view);
        }
    }
}
