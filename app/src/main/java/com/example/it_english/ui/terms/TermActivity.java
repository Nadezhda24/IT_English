package com.example.it_english.ui.terms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.it_english.R;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        Bundle arguments = getIntent().getExtras();
        Term term = new Term();
        term.setId(arguments.getInt("id"));
        term.setName(arguments.getString("name"));
        term.setDescription(arguments.getString("description"));

        TextView name = findViewById(R.id.Name);
        name.setText(term.getName());

        TextView description = findViewById(R.id.Description);
        description.setText(term.getDescription());

    }
}