//package com.ntdvv.it_english.ui;
//
//import android.content.res.Configuration;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.webkit.WebView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.ntdvv.it_english.HttpHandler;
//import com.ntdvv.it_english.R;
//import com.ntdvv.it_english.ui.languages.Language;
//
//public class ReferenceActivity extends AppCompatActivity
//{
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_language);
//
//        HttpHandler sh = new HttpHandler();
//
//        Bundle arguments = getIntent().getExtras();
//        Language language = new Language();
//        language.setId(arguments.getInt("id"));
//        language.setTitle(arguments.getString("name"));
//        language.setDescription(arguments.getString("description"));
//        language.setIconPath(arguments.getString("icon"));
//
//        TextView name = findViewById(R.id.Name);
//        name.setText(language.getTitle());
//
//        WebView description = (WebView) findViewById(R.id.Description);
//        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        switch (currentNightMode) {
//            case Configuration.UI_MODE_NIGHT_NO:
//            {
//                // Нyжно, иначе фон текста более белый, чем основной фон
//                description.setBackgroundColor(Color.TRANSPARENT);
//                String referenceItemText = String.format(getResources().getString(R.string.ReferenceItemModeNightNoTemplate), language.getDescription());
//                description.loadData(referenceItemText, "text/html", "UTF-8");
//                break;
//            }
//
//            case Configuration.UI_MODE_NIGHT_YES:
//            {
//                description.setBackgroundColor(Color.TRANSPARENT);
//                String referenceItemText = String.format(getResources().getString(R.string.ReferenceItemModeNightYesTemplate), language.getDescription());
//                description.loadData(referenceItemText, "text/html", "UTF-8");
//                break;
//            }
//        }
//
//        // ImageView Icon = findViewById(R.id.Img);
//        //Icon.setImageBitmap(HttpHandler.openImage(language.getIconPath(), this));
//        ImageView Icon = findViewById(R.id.Img);
//        Icon.setImageBitmap( sh.urlToBitmap(language.getIconPath()));
//    }
//}
