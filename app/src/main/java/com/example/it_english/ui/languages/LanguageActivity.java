package com.example.it_english.ui.languages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_english.HttpHandler;
import com.example.it_english.R;
import com.example.it_english.ui.profession.Profession;

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

        TextView description = findViewById(R.id.Description);
        description.setText(language.getDescription());

        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(language.getIconPath()));
    }
}