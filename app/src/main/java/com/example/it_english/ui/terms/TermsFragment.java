package com.example.it_english.ui.terms;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.it_english.HttpHandler;
import com.example.it_english.R;
import com.example.it_english.databinding.FragmentTermsBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class TermsFragment extends Fragment {

    private FragmentTermsBinding binding;
    ArrayList<Term> Terms = new ArrayList<Term>();
    String jsonStr = null;
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_terms";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms, container, false);

        setInitialData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        TermAdapter.OnTermClickListener termClickListener = new TermAdapter.OnTermClickListener() {
            @Override
            public void onTermClick(Term term, int position) {

                Toast.makeText(getActivity(), "Был выбран пункт " + term.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        TermAdapter TermAdapter = new TermAdapter(getActivity(), Terms, termClickListener);

        recyclerView.setAdapter(TermAdapter);

        return view;

    }

    private void setInitialData(){
        new GetData().execute();
       Terms.add(new Term(1, jsonStr, "Программист", R.drawable.ic_dashboard_black_24dp));

    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            return null;
        }
    }

}



