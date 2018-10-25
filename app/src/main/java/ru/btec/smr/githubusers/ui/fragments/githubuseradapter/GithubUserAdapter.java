package ru.btec.smr.githubusers.ui.fragments.githubuseradapter;

import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ru.btec.smr.githubusers.dbrealm.DbGithubUser;

public class GithubUserAdapter extends RealmRecyclerViewAdapter<DbGithubUser, GithubUserHolder> {

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<>();

   public GithubUserAdapter(OrderedRealmCollection<DbGithubUser> data) {
        super(data, true);
        // Only set this if the model class has a primary key that is also a integer or long.
        // In that case, {@code getItemId(int)} must also be overridden to return the key.
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#hasStableIds()
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#getItemId(int)
        setHasStableIds(true);
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }

    Set<Integer> getCountersToDelete() {
        return countersToDelete;
    }

    @Override
    public GithubUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GithubUserHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(GithubUserHolder holder, int position) {
        holder.bind( getItem(position));
    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return getItem(index).getId();
    }

//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title;
//        CheckBox deletedCheckBox;
//        public Item data;
//
//        MyViewHolder(View view) {
//            super(view);
//            title = view.findViewById(R.id.textview);
//            deletedCheckBox = view.findViewById(R.id.checkBox);
//        }
//    }
}
