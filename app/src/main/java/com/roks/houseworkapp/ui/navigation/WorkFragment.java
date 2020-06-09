package com.roks.houseworkapp.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
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
        FragmentActivity activity = getActivity();

        final WorkAdapter adapter = new WorkAdapter(activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((activity)));

        workViewModel = new ViewModelProvider(this).get(WorkViewModel.class);

        // Update the cached copy of the work in the adapter.
        workViewModel.getAllWork().observe(getViewLifecycleOwner(), adapter::setAllWork);

        FloatingActionButton fab = view.findViewById(R.id.workPageFab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(activity, NewWorkActivity.class);
            startActivityForResult(intent, NEW_WORK_REQUEST_CODE);
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORK_REQUEST_CODE && resultCode == RESULT_OK) {
            WorkEntity work = new WorkEntity(Objects.requireNonNull(data.getStringExtra(NewWorkActivity.WORK_NAME)), Objects.requireNonNull(data.getIntExtra(NewWorkActivity.WORK_SCORE, 0)));
            workViewModel.insert(work);
        } else {
            Toast.makeText(
                    getActivity(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}