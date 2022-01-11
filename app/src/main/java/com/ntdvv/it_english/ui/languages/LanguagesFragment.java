package com.ntdvv.it_english.ui.languages;

import static com.ntdvv.it_english.MainActivity.APP_PREFERENCES;
import static com.ntdvv.it_english.MainActivity.LANGUAGE;
import static com.ntdvv.it_english.MainActivity.TERMS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;
import com.ntdvv.it_english.databinding.FragmentLanguagesBinding;
import com.ntdvv.it_english.ui.terms.Term;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class LanguagesFragment  extends Fragment {

    private FragmentLanguagesBinding binding;
    ArrayList<Language> Languages = new ArrayList<Language>();
    String jsonRes = null;
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_languages";
    RecyclerView.Adapter LanguageAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_languages, container, false);

        setInitialData();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        LanguageAdapter.OnLanguageClickListener languageClickListener = new LanguageAdapter.OnLanguageClickListener() {
            @Override
            public void onLanguageClick(Language language, int position) {
                Intent intent=new Intent(getContext(), LanguageActivity.class);

                intent.putExtra("id", language.getId());
                intent.putExtra("name", language.getName());
                intent.putExtra("description", language.getDescription());
                intent.putExtra("icon", language.getIconPath());

                startActivity(intent);
            }

        };

        LanguageAdapter = new LanguageAdapter(getActivity(), Languages, languageClickListener);

        recyclerView.setAdapter(LanguageAdapter);

        return view;

    }


    private void setInitialData(){
        SharedPreferences mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String jsonRes = mSettings.getString(LANGUAGE,"");
        try {
            JSONObject json = new JSONObject("{\"languages\": " + jsonRes + " }");
            JSONArray arr = json.getJSONArray("languages");
            for (int i=0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("title");
                String description = obj.getString("description");
                String img = obj.getString("img");

                Languages.add(new Language(id, name, description, HttpHandler.openImage(img, this.getActivity()), img));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
