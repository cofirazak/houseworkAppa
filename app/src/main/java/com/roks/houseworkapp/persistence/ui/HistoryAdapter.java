package com.roks.houseworkapp.persistence.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;
import com.roks.houseworkapp.ui.navigation.HistoryFragment;

import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final LayoutInflater mInflater;
    private List<HistoryEntity> history; // Cached copy of history
    private Context context;
    private HistoryFragment fragment;

    public HistoryAdapter(Context context, HistoryFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.history_recyclerview_item, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        if (history != null) {
            HistoryEntity current = history.get(position);
            holder.historyItemViewName.setText(current.getName());
            holder.historyItemViewScore.setText(String.format(Locale.ENGLISH, "%d", current.getScore()));
            holder.historyItemDate.setTag(current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.historyItemViewName.setText("Нет записи");
            holder.historyItemViewScore.setText("-");
        }
    }

    public void setAllHistory(List<HistoryEntity> history) {
        this.history = history;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // work has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (history != null)
            return history.size();
        else return 0;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView historyItemViewName;
        private final TextView historyItemViewScore;
        private final TextView historyItemDate;

        private HistoryViewHolder(View itemView) {
            super(itemView);
            historyItemViewName = itemView.findViewById(R.id.history_name);
            historyItemViewScore = itemView.findViewById(R.id.history_score);
            historyItemDate = itemView.findViewById(R.id.history_date);
        }
    }
}
