package com.example.it_english.ui.trends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.it_english.R;
import com.example.it_english.ui.terms.Term;

public class TrendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);


        Bundle arguments = getIntent().getExtras();
        Trend trend = new Trend();
        trend.setId(arguments.getInt("id"));
        trend.setName(arguments.getString("name"));
        trend.setDescription(arguments.getString("description"));

        TextView name = findViewById(R.id.Name);
        name.setText(trend.getName());

        TextView description = findViewById(R.id.Description);
        description.setText(trend.getDescription());
    }
}