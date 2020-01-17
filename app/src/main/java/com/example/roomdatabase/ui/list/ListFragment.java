package com.example.roomdatabase.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.helper.RecyclerViewClick;
import com.example.roomdatabase.ui.add_text.room.DatabaseClient;
import com.example.roomdatabase.ui.add_text.room.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;
    private RecyclerView recyclerView;
    private FeedAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
            view = inflater.inflate(R.layout.fragment_dashboard, container, false);
            recyclerView = view.findViewById(R.id.rec_feedlist);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setHasFixedSize(true);
            Objects.requireNonNull(recyclerView.getLayoutManager()).setMeasurementCacheEnabled(false);


            listViewModel.getData().observe(Objects.requireNonNull(getActivity()), new Observer<List<Recipe>>() {
                @Override
                public void onChanged(final List<Recipe> recipes) {
                    if (recipes != null) {
                        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
                        adapter = new FeedAdapter(getActivity(), recipes);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

            recyclerView.addOnItemTouchListener(new RecyclerViewClick.RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerViewClick.RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }
                @Override
                public void onItemLongClick(View view, int position) {
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
    }
}