package com.example.it_english.ui.trends;

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
import com.example.it_english.databinding.FragmentTrendsBinding;
import com.example.it_english.ui.terms.TermsViewModel;

public class TrendsFragment extends Fragment {
    private TrendsViewModel trendsViewModel;
    private FragmentTrendsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TrendsViewModel.class);

        binding = FragmentTrendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTrends;
        trendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
