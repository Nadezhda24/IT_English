package com.ntdvv.it_english.ui.profession;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;

public class ProfessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);

        HttpHandler sh = new HttpHandler();

        Bundle arguments = getIntent().getExtras();
        Profession profession = new Profession();
        profession.setId(arguments.getInt("id"));
        profession.setName(arguments.getString("name"));
        profession.setDescription(arguments.getString("description"));
        profession.setIconPath(arguments.getString("icon"));

        TextView name = findViewById(R.id.Name);
        name.setText(profession.getName());

        WebView description = (WebView) findViewById(R.id.Description);
        description.loadData("<html><body>" + profession.getDescription() + "</body></html>","text/html", "UTF-8");


        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(profession.getIconPath()));

    }
}