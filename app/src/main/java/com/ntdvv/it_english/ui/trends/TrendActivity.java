package com.ntdvv.it_english.ui.trends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;

public class TrendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);

        HttpHandler sh = new HttpHandler();

        Bundle arguments = getIntent().getExtras();
        Trend trend = new Trend();
        trend.setId(arguments.getInt("id"));
        trend.setName(arguments.getString("name"));
        trend.setDescription(arguments.getString("description"));
        trend.setIconPath(arguments.getString("icon"));

        TextView name = findViewById(R.id.Name);
        name.setText(trend.getName());

        WebView description = (WebView) findViewById(R.id.Description);
        description.loadData("<html><body>" + trend.getDescription() + "</body></html>","text/html", "UTF-8");

        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(trend.getIconPath()));
    }
}