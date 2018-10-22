package ru.btec.smr.githubusers.ui.fragments.githubuseradapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.ViewGroup;

import java.util.Objects;

import ru.btec.smr.githubusers.R;
import ru.btec.smr.githubusers.model.GithubUser;

public class GithubUserAdapter extends PagedListAdapter<GithubUser, GithubUserHolder> {
    private NetworkState networkState;

    public GithubUserAdapter() {
        super(UserDiffCallback);
    }

    @NonNull
    @Override
    public GithubUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return GithubUserHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubUserHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.githubuser_list_item;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    public void setNetworkState(NetworkState newNetworkState) {
        if (getCurrentList() != null) {
            if (getCurrentList().size() != 0) {
                NetworkState previousState = this.networkState;
                boolean hadExtraRow = hasExtraRow();
                this.networkState = newNetworkState;
                boolean hasExtraRow = hasExtraRow();
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount());
                    } else {
                        notifyItemInserted(super.getItemCount());
                    }
                } else if (hasExtraRow && previousState != newNetworkState) {
                    notifyItemChanged(getItemCount() - 1);
                }
            }
        }
    }

    private static DiffUtil.ItemCallback<GithubUser> UserDiffCallback = new DiffUtil.ItemCallback<GithubUser>() {
        @Override
        public boolean areItemsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}



