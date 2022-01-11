package com.ntdvv.it_english;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ntdvv.it_english.databinding.ActivityMainBinding;
import com.ntdvv.it_english.ui.terms.Term;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String APP_PREFERENCES = "saveValue";
    public static final String TERMS = "TERMS";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String PROFESSION = "PROFESSION";
    public static final String TRENDS = "TRENDS";

    SharedPreferences preferences;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.terms, R.id.languages, R.id.profession, R.id.trends)
                .build();
        NavController navController = Navigation.findNavController((Activity) this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        try {
            if (preferences.getString(TRENDS, "") == "") {
                dialog = ProgressDialog.show(this, "",
                        "Loading. Please wait...", true);
                new GetData().execute(this);
            }
        } catch (Exception e) { //TODO: сделать нормальное решение для catch

        }
    }

    private class GetData extends AsyncTask<Context, Void, Void> {
        Context context;
        String urlTerms = "http://q90932z7.beget.tech/server.php?action=select_terms";
        String urlLanguages = "http://q90932z7.beget.tech/server.php?action=select_languages";
        String urlProfession = "http://q90932z7.beget.tech/server.php?action=select_professions";
        String urlTrends = "http://q90932z7.beget.tech/server.php?action=select_trends";

        @Override
        protected Void doInBackground(Context... contexts) {
            context = contexts[0];


            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(urlTerms);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TERMS, jsonStr);
            editor.apply();
            try {
                JSONObject json = new JSONObject("{\"terms\": " + jsonStr + " }");
                JSONArray arr = json.getJSONArray("terms");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String img = obj.getString("img");
                    sh.urlToBitmap(img, context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonStr = sh.makeServiceCall(urlLanguages);
            editor.putString(LANGUAGE, jsonStr);
            editor.apply();
            try {
                JSONObject json = new JSONObject("{\"languages\": " + jsonStr + " }");
                JSONArray arr = json.getJSONArray("languages");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String img = obj.getString("img");
                    sh.urlToBitmap(img, context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonStr = sh.makeServiceCall(urlProfession);
            editor.putString(PROFESSION, jsonStr);
            editor.apply();
            try {
                JSONObject json = new JSONObject("{\"professions\": " + jsonStr + " }");
                JSONArray arr = json.getJSONArray("professions");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String img = obj.getString("img");
                    sh.urlToBitmap(img, context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonStr = sh.makeServiceCall(urlTrends);
            editor.putString(TRENDS, jsonStr);
            editor.apply();
            try {
                JSONObject json = new JSONObject("{\"trends\": " + jsonStr + " }");
                JSONArray arr = json.getJSONArray("trends");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String img = obj.getString("img");
                    sh.urlToBitmap(img, context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        //выполняется после doInBackground
        @Override
        protected void onPostExecute(Void v) {
            finish();
            startActivity(getIntent());
        }
    }

}