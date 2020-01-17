package com.example.roomdatabase.ui.clearall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.roomdatabase.R;
import com.example.roomdatabase.ui.add_text.room.DatabaseClient;
import com.example.roomdatabase.ui.add_text.room.Recipe;
import com.example.roomdatabase.ui.add_text.room.RecipeDao;

import java.util.List;

public class ClearAllFragment extends Fragment {

    private ClearAllViewModel clearAllViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        clearAllViewModel = ViewModelProviders.of(this).get(ClearAllViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        clearAllViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        List<Recipe> recipeList = DatabaseClient.getInstance(getActivity()).getAppDatabase().recipeDao().getAll();
                        for (Recipe recipe: recipeList) {
                            DatabaseClient.getInstance(getActivity()).getAppDatabase().recipeDao().delete(recipe);
                        }

                    }
                });
                thread.start();




            }
        });
        return root;
    }
}