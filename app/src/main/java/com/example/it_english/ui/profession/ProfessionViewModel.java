package com.example.it_english.ui.profession;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ProfessionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfessionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Profession fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}