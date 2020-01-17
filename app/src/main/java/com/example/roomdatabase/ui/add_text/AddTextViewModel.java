package com.example.roomdatabase.ui.add_text;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddTextViewModel extends ViewModel {

    private MutableLiveData<Home> liveData;
    public MutableLiveData<String> errorInput = new MutableLiveData<>();
    public MutableLiveData<String> input = new MutableLiveData<>();


    LiveData<Home> getData(){
        liveData=new MutableLiveData<>();
        return liveData;
    }


    public void onClicked(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Home home=new Home(input.getValue());
                if(!home.isInputValid()){
                    errorInput.setValue("Enter a valid");
                }
                else {
                    errorInput.setValue(null);
                }
                liveData.setValue(home);

            }
        }, 200);

    }

}