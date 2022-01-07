package com.ntdvv.it_english.ui.terms;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;
import com.ntdvv.it_english.databinding.FragmentTermsBinding;

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

                Intent intent =new Intent(getContext(),TermActivity.class);

                intent.putExtra("id", term.getId());
                intent.putExtra("name", term.getName());
                intent.putExtra("description", term.getDescription());
                intent.putExtra("icon", term.getIconPath());

                startActivity(intent);
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

        }

    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        HttpHandler sh = new HttpHandler();
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
                JSONObject json = new JSONObject("{\"terms\": " + jsonRes + " }");
                JSONArray arr = json.getJSONArray("terms");
                for (int i=0; i < arr.length(); i++ ){
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("title");
                    String description = obj.getString("description");
                    String img = obj.getString("img");

                    Terms.add(new Term(id, name, description, sh.urlToBitmap(img), img));

                }
            } catch (JSONException e) {
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



