package com.example.it_english.ui.terms;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_english.R;
import com.example.it_english.databinding.FragmentTermsBinding;

import java.util.ArrayList;

public class TermsFragment extends Fragment {

    private FragmentTermsBinding binding;
    ArrayList<Term> Terms = new ArrayList<Term>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms, container, false);

        setInitialData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        TermAdapter.OnTermClickListener taskClickListener = new TermAdapter.OnTermClickListener() {
            @Override
            public void onTaskClick(Term term, int position) {

                Toast.makeText(getActivity(), "Был выбран пункт " + term.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        TermAdapter TermAdapter = new TermAdapter(getActivity(), Terms, taskClickListener);

        recyclerView.setAdapter(TermAdapter);

        return view;


        /*termsViewModel =
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
        return root;*/

    }

    private void setInitialData(){
        for (int i=0; i < 20; i++)
        Terms.add(new Term(1, "Программист", "Программист", R.drawable.ic_dashboard_black_24dp));


    }


}
