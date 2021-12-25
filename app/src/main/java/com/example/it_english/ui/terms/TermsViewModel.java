package com.example.it_english.ui.terms;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class TermsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TermsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Terms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
