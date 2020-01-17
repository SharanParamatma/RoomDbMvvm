package com.example.roomdatabase.ui.add_text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.roomdatabase.R;
import com.example.roomdatabase.databinding.FragmentHomeBinding;
import com.example.roomdatabase.ui.add_text.room.DatabaseClient;
import com.example.roomdatabase.ui.add_text.room.Recipe;
import com.example.roomdatabase.ui.list.ListViewModel;

import java.util.List;

public class AddTextFragment extends Fragment {

    public AddTextFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding binding=FragmentHomeBinding.inflate(inflater,container,false);
        AddTextViewModel addTextViewModel = ViewModelProviders.of(this).get(AddTextViewModel.class);
        binding.setAddTextViewModel(addTextViewModel);
        binding.setLifecycleOwner(this);
        addTextViewModel.getData().observe(this, new Observer<Home>() {
            @Override
            public void onChanged(final Home home) {
                if (home !=null && home.getInput().length()>=5){
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Recipe recipe= new Recipe();
                            recipe.setMessage(home.getInput());
                            DatabaseClient.getInstance(getActivity()).getAppDatabase().recipeDao().insert(recipe);
                        }
                    });
                    thread.start();

                }
            }
        });
        return binding.getRoot();
    }



}