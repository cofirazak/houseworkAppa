package com.roks.houseworkapp.ui.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roks.houseworkapp.R;
import com.roks.houseworkapp.persistence.ui.ScoreAdapter;
import com.roks.houseworkapp.persistence.viewmodel.ScoreViewModel;


public class ScoreFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score_page, container, false);

        RecyclerView recyclerViewWeek = view.findViewById(R.id.scorePageRecyclerView);
        Context context = getContext();

        final ScoreAdapter adapter = new ScoreAdapter(context);
        recyclerViewWeek.setAdapter(adapter);
        recyclerViewWeek.setLayoutManager(new LinearLayoutManager((context)));

        ScoreViewModel scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

        // Update the cached copy of the history in the adapter.
        scoreViewModel.getHistoryStat().observe(getViewLifecycleOwner(), adapter::setHistoryStat);

        return view;
    }
}
