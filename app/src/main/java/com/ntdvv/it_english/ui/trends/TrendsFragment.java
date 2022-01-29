package com.ntdvv.it_english.ui.trends;

import static com.ntdvv.it_english.MainActivity.APP_PREFERENCES;
import static com.ntdvv.it_english.MainActivity.TRENDS;

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
import com.ntdvv.it_english.databinding.FragmentTrendsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrendsFragment extends Fragment {
    private FragmentTrendsBinding binding;
    HttpHandler sh = new HttpHandler();
    ArrayList<Trend> Trends = new ArrayList<Trend>();
    private static String  url =  "http://q90932z7.beget.tech/server.php?action=select_trends";
    RecyclerView.Adapter TrendAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trends, container, false);

        setInitialData();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        TrendAdapter.OnTrendClickListener trendClickListener = new TrendAdapter.OnTrendClickListener() {
            @Override
            public void onTrendClick(Trend trend, int position) {
                Intent intent=new Intent(getContext(),TrendActivity.class);

                intent.putExtra("id", trend.getId());
                intent.putExtra("name", trend.getTitle());
                intent.putExtra("description", trend.getDescription());
                intent.putExtra("icon", trend.getIconPath());

                startActivity(intent);

            }
        };

        TrendAdapter = new TrendAdapter(getActivity(), Trends, trendClickListener);

        recyclerView.setAdapter(TrendAdapter);

        return view;

    }


    private void setInitialData(){

        SharedPreferences mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String jsonRes = mSettings.getString(TRENDS,"");
        try {
            JSONObject json = new JSONObject("{\"trends\": " + jsonRes + " }");
            JSONArray arr = json.getJSONArray("trends");
            for (int i=0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                int id = obj.getInt("id");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String img = obj.getString("img");

                Trends.add(new Trend(id, title, description, sh.urlToBitmap(img), img));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
