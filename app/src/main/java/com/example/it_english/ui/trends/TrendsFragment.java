package com.example.it_english.ui.trends;

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
import com.example.it_english.databinding.FragmentTrendsBinding;

import java.util.ArrayList;

public class TrendsFragment extends Fragment {

    private FragmentTrendsBinding binding;
    ArrayList<Trend> Trends = new ArrayList<Trend>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trends, container, false);

        setInitialData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        TrendAdapter.OnTrendClickListener termClickListener = new TrendAdapter.OnTrendClickListener() {
            @Override
            public void onTrendClick(Trend trend, int position) {

                Toast.makeText(getActivity(), "Был выбран пункт " + trend.getName(),
                        Toast.LENGTH_SHORT).show();
            }

        };

        TrendAdapter TrendAdapter = new TrendAdapter(getActivity(), Trends, termClickListener);

        recyclerView.setAdapter(TrendAdapter);

        return view;

    }

    private void setInitialData(){
        for (int i=0; i < 20; i++)
            Trends.add(new Trend(1, "Тренд", "Тренд", R.drawable.ic_dashboard_black_24dp));
    }


}
