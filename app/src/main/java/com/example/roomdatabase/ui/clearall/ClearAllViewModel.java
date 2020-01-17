package com.example.roomdatabase.ui.clearall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClearAllViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClearAllViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Clear All");
    }

    public LiveData<String> getText() {
        return mText;
    }
}