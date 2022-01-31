package com.ntdvv.it_english.ui;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.HttpHandler;
import com.ntdvv.it_english.R;
import com.ntdvv.it_english.ui.languages.Language;

public class ReferenceActivity extends AppCompatActivity
{
    protected void loadReferenceItemData()
    {

        HttpHandler sh = new HttpHandler();

        Bundle arguments = getIntent().getExtras();
        ReferenceItem referenceItem = new Language();
        referenceItem.setId(arguments.getInt("id"));
        referenceItem.setTitle(arguments.getString("name"));
        referenceItem.setDescription(arguments.getString("description"));
        referenceItem.setIconPath(arguments.getString("icon"));

        TextView name = findViewById(R.id.Name);
        name.setText(referenceItem.getTitle());

        WebView description = (WebView) findViewById(R.id.Description);
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
            {
                // Нyжно, иначе фон текста более белый, чем основной фон
                description.setBackgroundColor(Color.TRANSPARENT);
                String referenceItemText = String.format(getResources().getString(R.string.referenceItemModeNightNoHtmlTemplate), referenceItem.getDescription());
                description.loadData(referenceItemText, "text/html", "UTF-8");
                break;
            }

            case Configuration.UI_MODE_NIGHT_YES:
            {
                description.setBackgroundColor(Color.TRANSPARENT);
                String referenceItemText = String.format(getResources().getString(R.string.referenceItemModeNightYesHtmlTemplate), referenceItem.getDescription());
                description.loadData(referenceItemText, "text/html", "UTF-8");
                break;
            }
        }

        ImageView Icon = findViewById(R.id.Img);
        Icon.setImageBitmap( sh.urlToBitmap(referenceItem.getIconPath()));
    }
}
