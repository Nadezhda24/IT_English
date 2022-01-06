package com.example.it_english.ui.terms;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.it_english.HttpHandler;
import com.example.it_english.R;
import com.example.it_english.databinding.FragmentTermsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TermsFragment extends Fragment {

    private FragmentTermsBinding binding;
    ArrayList<Term> Terms = new ArrayList<Term>();
    String jsonRes = null;
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_terms";
    RecyclerView.Adapter TermAdapter;


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

        TermAdapter = new TermAdapter(getActivity(), Terms, termClickListener);

        recyclerView.setAdapter(TermAdapter);

        return view;

    }


    private void setInitialData(){
        try {
            new GetData().execute().get();
        } catch (Exception e) { //TODO: сделать нормальное решение для catch
            Terms.add(new Term(1, "Проверьте интернет и еще раз зайдите в раздел", "", R.drawable.warning));
        }

    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            jsonRes = jsonStr;
            return null;
        }
        //выполняется после doInBackground
        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject json = new JSONObject("{\"terms\": [{\"id\":\"2\",\"title\":\"term 2\",\"description\":\"Some description 2\",\"img\":null},{\"id\":\"3\",\"title\":\"term 3\",\"description\":\"Some description 3\",\"img\":null},{\"id\":\"4\",\"title\":\"term 5\",\"description\":\"desc 5\",\"img\":null},{\"id\":\"5\",\"title\":\"term 6\",\"description\":\"desc 6\",\"img\":\"terms_img_term_6.gif\"},{\"id\":\"6\",\"title\":\"term 7\",\"description\":\"desc 7\",\"img\":\"terms_img_term_7.gif\"}]}");
                JSONArray arr = json.getJSONArray("terms");
                for (int i=0; i < arr.length(); i++ ){
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("title");
                    String description = obj.getString("description");
                    String img = obj.getString("img");

                    Terms.add(new Term(id, name, description, R.drawable.ic_dashboard_black_24dp));

                }
            } catch (JSONException e) {
                Terms.add(new Term(1, "1", "2", R.drawable.ic_dashboard_black_24dp));
                 e.printStackTrace();
            }


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   TermAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}



