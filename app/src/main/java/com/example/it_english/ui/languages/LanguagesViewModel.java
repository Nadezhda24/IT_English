package com.example.it_english.ui.languages;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LanguagesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LanguagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Languages fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
