package com.example.it_english.ui.profession;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.it_english.R;
import com.example.it_english.databinding.FragmentTermsBinding;

import java.util.ArrayList;

public class ProfessionFragment extends Fragment {
    private FragmentTermsBinding binding;
    ArrayList<Profession> Professions = new ArrayList<Profession>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profession, container, false);

        setInitialData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ProfessionAdapter.OnProfessionClickListener professionClickListener = new ProfessionAdapter.OnProfessionClickListener() {
            @Override
            public void onProfessionClick(Profession profession, int position) {

                Toast.makeText(getActivity(), "Был выбран пункт " + profession.getName(),
                        Toast.LENGTH_SHORT).show();
            }

        };

        ProfessionAdapter ProfessionAdapter = new ProfessionAdapter(getActivity(), Professions, professionClickListener);

        recyclerView.setAdapter(ProfessionAdapter);

        return view;

    }

    private void setInitialData(){
        for (int i=0; i < 20; i++)
            Professions.add(new Profession(1, "Профессия", "Профессия", R.drawable.ic_dashboard_black_24dp));
    }


}
