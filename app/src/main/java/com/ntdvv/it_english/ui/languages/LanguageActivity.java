package com.ntdvv.it_english.ui.languages;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        HttpHandler sh = new HttpHandler();

        Bundle arguments = getIntent().getExtras();
        Language language = new Language();
        language.setId(arguments.getInt("id"));
        language.setName(arguments.getString("name"));
        language.setDescription(arguments.getString("description"));
        language.setIconPath(arguments.getString("icon"));

        TextView name = findViewById(R.id.Name);
        name.setText(language.getName());

        WebView description = (WebView) findViewById(R.id.Description);
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                description.setBackgroundColor(Color.TRANSPARENT); //нyжно, иначе фон текста более белый, чем основной фон
                description.loadData("<html>" +
                        "<head>" +
                        "    <style>" +
                        "      p { text-indent: 25px; }" +
                        "    </style>" +
                        "  <head>"+
                        "<body style='text-align: justify;'>"
                        + language.getDescription() +
                        "</body>" +
                        "</html>",
                        "text/html", "UTF-8");
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                description.setBackgroundColor(Color.TRANSPARENT);
                description.loadData("<html>" +
                        "<head>" +
                        "    <style>" +
                        "      p { text-indent: 25px; }" +
                        "    </style>" +
                        "  <head>"+
                        "<body style='text-align: justify;'>" +
                        "<font color='white'>" +
                        language.getDescription() +
                        "</body>" +
                        "</html>",
                        "text/html", "UTF-8");
                break;
        }

       // ImageView Icon = findViewById(R.id.Img);
        //Icon.setImageBitmap(HttpHandler.openImage(language.getIconPath(), this));
        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(language.getIconPath()));
    }
}