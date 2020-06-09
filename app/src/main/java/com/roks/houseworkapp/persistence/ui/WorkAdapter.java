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
import java.util.Locale;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    private final LayoutInflater mInflater;
    private List<WorkEntity> work; // Cached copy of work

    public WorkAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WorkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        if (work != null) {
            WorkEntity current = work.get(position);
            holder.workItemViewName.setText(current.getName());
            holder.workItemViewScore.setText(String.format(Locale.ENGLISH, "%d", current.getScore()));
        } else {
            // Covers the case of data not being ready yet.
            holder.workItemViewName.setText("Нет записи");
            holder.workItemViewScore.setText("-");
        }
    }

    public void setAllWork(List<WorkEntity> work) {
        this.work = work;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // work has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (work != null)
            return work.size();
        else return 0;
    }

    class WorkViewHolder extends RecyclerView.ViewHolder {
        private final TextView workItemViewName;
        private final TextView workItemViewScore;

        private WorkViewHolder(View itemView) {
            super(itemView);
            workItemViewName = itemView.findViewById(R.id.work_name);
            workItemViewScore = itemView.findViewById(R.id.work_score);
        }
    }
}
