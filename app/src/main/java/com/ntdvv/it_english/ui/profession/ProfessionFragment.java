package com.ntdvv.it_english.ui.profession;

import static com.ntdvv.it_english.MainActivity.APP_PREFERENCES;
import static com.ntdvv.it_english.MainActivity.PROFESSION;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ntdvv.it_english.databinding.FragmentProfessionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfessionFragment extends Fragment {
    private FragmentProfessionBinding binding;
    ArrayList<Profession> Professions = new ArrayList<Profession>();
    String jsonRes = null;
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_professions";
    HttpHandler sh = new HttpHandler();
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
                intent.putExtra("name", profession.getTitle());
                intent.putExtra("description", profession.getDescription());
                intent.putExtra("icon", profession.getIconPath());

                startActivity(intent);
            }

        };

        ProfessionAdapter = new ProfessionAdapter(getActivity(), Professions, professionClickListener);

        recyclerView.setAdapter(ProfessionAdapter);

        return view;

    }


    private void setInitialData(){
        SharedPreferences mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String jsonRes = mSettings.getString(PROFESSION,"");
        try {
            JSONObject json = new JSONObject("{\"professions\": " + jsonRes + " }");
            JSONArray arr = json.getJSONArray("professions");
            for (int i=0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                int id = obj.getInt("id");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String img = obj.getString("img");

                Professions.add(new Profession(id, title, description, sh.urlToBitmap(img), img));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
