package ru.btec.smr.githubusers.ui.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.interfaces.OnGithubuserListItemSelectedListener;
import ru.btec.smr.githubusers.model.GithubUser;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.GithubUsersHolder> {
    private List<GithubUser> githubUsers;
    private OnGithubuserListItemSelectedListener callbackActivity;

    GithubUserAdapter(List<GithubUser> githubUsers, Context context) {
        this.githubUsers = githubUsers;
        callbackActivity = (OnGithubuserListItemSelectedListener) context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GithubUsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.githubuser_list_item, parent, false);
        return new GithubUsersHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubUsersHolder holder, int position) {
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


    class GithubUsersHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        CardView listItemCardView;

        GithubUsersHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.item_title);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
            listItemCardView = (CardView) itemView.findViewById(R.id.list_item_card_view);
        }
    }

}



