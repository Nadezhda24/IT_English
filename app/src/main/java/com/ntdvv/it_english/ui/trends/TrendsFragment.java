package com.ntdvv.it_english.ui.trends;

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

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;
import com.ntdvv.it_english.databinding.FragmentTrendsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class TrendsFragment extends Fragment {
    private FragmentTrendsBinding binding;
    ArrayList<Trend> Trends = new ArrayList<Trend>();
    String jsonRes = null;
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
                intent.putExtra("name", trend.getName());
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
        try {
            new TrendsFragment.GetData().execute().get();
        } catch (Exception e) { //TODO: сделать нормальное решение для catch
            e.printStackTrace();
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

                    Trends.add(new Trend(id, name, description, sh.urlToBitmap(img), img));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TrendAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public Bitmap getBitmapFromURL(String src) {
        try {

            java.net.URL url = new java.net.URL(src);
            HttpURLConnection  urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");
            urlConnection.connect();

            InputStream input = urlConnection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
