package com.roks.houseworkapp.ui.navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roks.houseworkapp.NewWorkActivity;
import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;
import com.roks.houseworkapp.persistence.ui.WorkAdapter;
import com.roks.houseworkapp.persistence.viewmodel.WorkViewModel;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class WorkFragment extends Fragment {

    private static final int NEW_WORK_REQUEST_CODE = 1;
    private WorkViewModel workViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_page, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.workPageRecyclerView);
        Context context = getContext();

        final WorkAdapter adapter = new WorkAdapter(context, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((context)));

        workViewModel = new ViewModelProvider(this).get(WorkViewModel.class);

        // Update the cached copy of the work in the adapter.
        workViewModel.getAllWork().observe(getViewLifecycleOwner(), adapter::setAllWork);

        FloatingActionButton fab = view.findViewById(R.id.workPageFab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewWorkActivity.class);
            startActivityForResult(intent, NEW_WORK_REQUEST_CODE);
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDirection) {
                AlertDialog.Builder workDoneDialog = new AlertDialog.Builder(context);
                workDoneDialog.setTitle("Удаление дела.");
                workDoneDialog.setMessage("Вы хотите удалить дело?");
                workDoneDialog.setIcon(R.drawable.ic_action_delete);
                workDoneDialog.setPositiveButton("Да",
                        (dialog, which) -> {
                            //Remove swiped item from list and notify the RecyclerView
                            int position = viewHolder.getAdapterPosition();
                            workViewModel.delete(adapter.getWorkByIndex(position));
                            adapter.notifyDataSetChanged();
                        });
                workDoneDialog.setNegativeButton("Нет", (dialog, which) -> adapter.notifyDataSetChanged());
                workDoneDialog.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORK_REQUEST_CODE && resultCode == RESULT_OK) {
            WorkEntity work = new WorkEntity(
                    Objects.requireNonNull(data.getStringExtra(NewWorkActivity.WORK_NAME)),
                    data.getIntExtra(NewWorkActivity.WORK_SCORE, 0));
            workViewModel.insert(work);
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}