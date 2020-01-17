package com.example.roomdatabase.ui.list;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase.ui.add_text.room.DatabaseClient;
import com.example.roomdatabase.ui.add_text.room.Recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewModel extends ViewModel {
    private Context context;

    public LiveData<List<Recipe>> getData() {
        MutableLiveData<List<Recipe>> mutableLiveData = new MutableLiveData<>();
        List<Recipe> recipeList = DatabaseClient.getInstance(context).getAppDatabase().recipeDao().getAll();
        mutableLiveData.setValue(recipeList);
        return mutableLiveData;
    }
}