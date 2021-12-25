package com.example.it_english.ui.profession;

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

import com.example.it_english.databinding.FragmentLanguagesBinding;
import com.example.it_english.databinding.FragmentProfessionBinding;
import com.example.it_english.ui.languages.LanguagesViewModel;

public class ProfessionFragment extends Fragment {
    private ProfessionViewModel professionViewModel;
    private FragmentProfessionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        professionViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProfessionViewModel.class);

        binding = FragmentProfessionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textProfession;
        professionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
