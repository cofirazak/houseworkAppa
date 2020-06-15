package com.roks.houseworkapp.persistence.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.ScoreEntity;
import com.roks.houseworkapp.ui.navigation.ScoreFragment;

import java.util.List;
import java.util.Locale;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private final LayoutInflater mInflater;
    private List<ScoreEntity> historyStat; // Cached copy of historyScore

    public ScoreAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.score_recyclerview_item, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        if (historyStat != null) {
            ScoreEntity current = historyStat.get(position);
            String weekScore = String.format(Locale.ENGLISH, "%d", current.getWeekScore());
            String monthScore = String.format(Locale.ENGLISH, "%d", current.getMonthScore());
            String totalScore = String.format(Locale.ENGLISH, "%d", current.getTotalScore());
            holder.scoreItemViewUserName.setText(current.getUserName());
            holder.scoreItemViewWeekScore.setText(weekScore.equals("null") ? "-" : weekScore);
            holder.scoreItemViewMonthScore.setText(monthScore.equals("null") ? "-" : weekScore);
            holder.scoreItemViewTotalScore.setText(totalScore.equals("null") ? "-" : weekScore);
        } else {
            // Covers the case of data not being ready yet.
            holder.scoreItemViewUserName.setText("Нет записи");
            holder.scoreItemViewWeekScore.setText("-");
            holder.scoreItemViewMonthScore.setText("-");
            holder.scoreItemViewTotalScore.setText("-");
        }
    }

    public void setHistoryStat(List<ScoreEntity> historyStat) {
        this.historyStat = historyStat;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // work has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (historyStat != null)
            return historyStat.size();
        else return 0;
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        private final TextView scoreItemViewUserName;
        private final TextView scoreItemViewWeekScore;
        private final TextView scoreItemViewMonthScore;
        private final TextView scoreItemViewTotalScore;

        private ScoreViewHolder(View itemView) {
            super(itemView);
            scoreItemViewUserName = itemView.findViewById(R.id.score_user_name);
            scoreItemViewWeekScore = itemView.findViewById(R.id.weekScore);
            scoreItemViewMonthScore = itemView.findViewById(R.id.monthScore);
            scoreItemViewTotalScore = itemView.findViewById(R.id.totalScore);
        }
    }
}
