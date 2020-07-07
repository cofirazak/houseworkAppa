package com.roks.houseworkapp.persistence.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;
import com.roks.houseworkapp.persistence.viewmodel.HistoryViewModel;
import com.roks.houseworkapp.ui.navigation.WorkFragment;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    private final LayoutInflater mInflater;
    private List<WorkEntity> work; // Cached copy of work
    private Context context;
    private WorkFragment fragment;

    public WorkAdapter(Context context, WorkFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        mInflater = LayoutInflater.from(context);
    }

    public WorkEntity getWorkByIndex(int index) {
        return work.get(index);
    }

    public void setAllWork(List<WorkEntity> work) {
        this.work = work;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.work_recyclerview_item, parent, false);
        Button button = itemView.findViewById(R.id.work_done_button);
        button.setOnClickListener(v -> {
            AlertDialog.Builder workDoneDialog = new AlertDialog.Builder(context);
            workDoneDialog.setTitle("Завершение дела.");
            workDoneDialog.setMessage("Вы хотите завершить дело?");
            workDoneDialog.setIcon(R.drawable.ic_action_complite);
            workDoneDialog.setPositiveButton("Да",
                    (dialog, which) -> {
                        HistoryViewModel historyViewModel = new ViewModelProvider(fragment)
                                .get(HistoryViewModel.class);
                        Optional<WorkEntity> clickedItem = work.stream().filter(work -> work.getId()
                                .equals(v.getTag())).findFirst();

                        HistoryEntity newHistoryEntity = new HistoryEntity(
                                Objects.requireNonNull(clickedItem.orElse(null)).getName(),
                                Objects.requireNonNull(clickedItem.orElse(null)).getScore(),
                                new Date(System.currentTimeMillis()));

                        historyViewModel.insert(newHistoryEntity);
                    });
            workDoneDialog.setNegativeButton("Нет", (dialog, which) -> {
            });
            workDoneDialog.show();
        });
        return new WorkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        if (work != null) {
            WorkEntity current = work.get(position);
            holder.workItemViewName.setText(current.getName());
            holder.workItemViewScore.setText(String.format(Locale.ENGLISH, "%d", current.getScore()));
            holder.workItemButtonId.setTag(current.getId());
        } else {
            // Covers the case of data not being ready yet.
            holder.workItemViewName.setText("Нет записи");
            holder.workItemViewScore.setText("-");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // work has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (work != null)
            return work.size();
        else return 0;
    }

    static class WorkViewHolder extends RecyclerView.ViewHolder {
        private final TextView workItemViewName;
        private final TextView workItemViewScore;
        private final Button workItemButtonId;

        private WorkViewHolder(View itemView) {
            super(itemView);
            workItemViewName = itemView.findViewById(R.id.work_name);
            workItemViewScore = itemView.findViewById(R.id.work_score);
            workItemButtonId = itemView.findViewById(R.id.work_done_button);
        }
    }
}
