package com.example.it_english.ui.terms;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_english.databinding.FragmentTermsBinding;

public class TermsFragment extends Fragment {
    private TermsViewModel termsViewModel;
    private FragmentTermsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        termsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TermsViewModel.class);

        binding = FragmentTermsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTerms;
        termsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
