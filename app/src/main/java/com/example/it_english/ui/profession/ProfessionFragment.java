package com.example.it_english.ui.profession;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.it_english.HttpHandler;
import com.example.it_english.R;
import com.example.it_english.databinding.FragmentProfessionBinding;
import com.example.it_english.databinding.FragmentTermsBinding;
import com.example.it_english.ui.terms.Term;
import com.example.it_english.ui.terms.TermActivity;
import com.example.it_english.ui.terms.TermAdapter;
import com.example.it_english.ui.terms.TermsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class ProfessionFragment extends Fragment {
    private FragmentProfessionBinding binding;
    ArrayList<Profession> Professions = new ArrayList<Profession>();
    String jsonRes = null;
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_professions";
    RecyclerView.Adapter ProfessionAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profession, container, false);

        setInitialData();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ProfessionAdapter.OnProfessionClickListener professionClickListener = new ProfessionAdapter.OnProfessionClickListener() {
            @Override
            public void onProfessionClick(Profession profession, int position) {
                Intent intent=new Intent(getContext(), ProfessionActivity.class);

                intent.putExtra("id", profession.getId());
                intent.putExtra("name", profession.getName());
                intent.putExtra("description", profession.getDescription());
                intent.putExtra("icon", profession.getIcon());

                startActivity(intent);
            }

        };

        ProfessionAdapter = new ProfessionAdapter(getActivity(), Professions, professionClickListener);

        recyclerView.setAdapter(ProfessionAdapter);

        return view;

    }


    private void setInitialData(){
        try {
            new ProfessionFragment.GetData().execute().get();
        } catch (Exception e) { //TODO: сделать нормальное решение для catch
            Professions.add(new Profession(1, "Проверьте интернет и еще раз зайдите в раздел", "", R.drawable.warning));
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
                JSONObject json = new JSONObject("{\"professions\": " + jsonRes + " }");
                JSONArray arr = json.getJSONArray("professions");
                for (int i=0; i < arr.length(); i++ ){
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("title");
                    String description = obj.getString("description");
                    String img = obj.getString("img");
                    //Drawable drawable = new BitmapDrawable(getResources(), getBitmapFromURL("http://q90932z7.beget.tech/img/terms_img_term_6.jpg"));
                    Professions.add(new Profession(id, name, description, R.drawable.warning));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ProfessionAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
