package com.example.it_english.ui.trends;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class TrendsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TrendsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Terms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}