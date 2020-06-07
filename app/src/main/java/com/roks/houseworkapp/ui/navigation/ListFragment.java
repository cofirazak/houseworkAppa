package com.roks.houseworkapp.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roks.houseworkapp.MainActivity;
import com.roks.houseworkapp.NewWorkActivity;
import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;
import com.roks.houseworkapp.persistence.ui.WorkAdapter;
import com.roks.houseworkapp.persistence.viewmodel.WorkViewModel;

import java.util.Objects;

public class ListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.first_page, container, false);
    }

//    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
//    private WorkViewModel workViewModel;
//
//
//    public void onCreateView(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
//        final WorkAdapter adapter = new WorkAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager((this));
//
//        workViewModel = new ViewModelProvider(this).get(WorkViewModel.class);
//
//        // Update the cached copy of the words in the adapter.
//        workViewModel.getAllWork().observe(this, adapter::setAllWork);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(view -> {
//            Intent intent = new Intent(ListFragment.this, NewWorkActivity.class);
//            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
//        });
//    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            WorkEntity word = new WorkEntity(Objects.requireNonNull(data.getStringExtra(NewWorkActivity.EXTRA_REPLY)), 1);
//            workViewModel.insert(word);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
}