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
import com.roks.houseworkapp.persistence.ui.HistoryAdapter;
import com.roks.houseworkapp.persistence.viewmodel.HistoryViewModel;


public class HistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_page, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyPageRecyclerView);
        Context context = getContext();

        final HistoryAdapter adapter = new HistoryAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((context)));

        HistoryViewModel historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        // Update the cached copy of the history in the adapter.
        historyViewModel.getAllHistory().observe(getViewLifecycleOwner(), adapter::setAllHistory);

        return view;
    }
}
