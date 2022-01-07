package com.ntdvv.it_english.ui.terms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        HttpHandler sh = new HttpHandler();

        Bundle arguments = getIntent().getExtras();
        Term term = new Term();
        term.setId(arguments.getInt("id"));
        term.setName(arguments.getString("name"));
        term.setDescription(arguments.getString("description"));
        term.setIconPath(arguments.getString("icon"));


        TextView name = findViewById(R.id.Name);
        name.setText(term.getName());

        TextView description = findViewById(R.id.Description);
        description.setText(term.getDescription());

        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(term.getIconPath()));

    }
}