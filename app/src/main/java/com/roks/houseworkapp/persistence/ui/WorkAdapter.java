package com.roks.houseworkapp.persistence.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    class WorkViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WorkViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<WorkEntity> mWords; // Cached copy of words

    public WorkAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    @NonNull
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WorkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        if (mWords != null) {
            WorkEntity current = mWords.get(position);
            holder.wordItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("Нет записи");
        }
    }

    public void setAllWork(List<WorkEntity> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}
