package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button en;
    String language;

    String engWord = "atlal";
    String sveWord = "basha";

    String EN_KEY;
    String SV_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar icon and text
        TextView textBarIcon = findViewById(R.id.toolBar_textView_icon);
        ImageView menuBarIcon = findViewById(R.id.toolBar_menu_icon);
        ImageView infoIcon = findViewById(R.id.toolBar_info_icon);
        ImageView playIcon = findViewById(R.id.toolBar_game_icon);
        textBarIcon.setText(R.string.menu);

        // icon pressed in toolbar
        menuBarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mainIntent();

            }
        });
        infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutIntent();
            }
        });
        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameIntent();
            }
        });

        en = findViewById(R.id.sv_button);


    }


// Button pressed in menu activity.
    public void play_button_pressed(View view) {
        gameIntent();
    }
    public void about_button_pressed(View view) {
        aboutIntent();
    }
// Intent activity
    public void gameIntent(){
        Intent intent = new Intent(this, Game.class);
        intent.putExtra(EN_KEY, engWord);
        intent.putExtra(SV_KEY, engWord);
        startActivity(intent);
    }
    public void aboutIntent(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
    public void mainIntent(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }







    public void setLanguage(String enSV) {
        String languageToLoad = enSV; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);

    }

    public void svPressed(View view) {
        setLanguage("sv");
        Intent intent = new Intent(this, Game.class);
        intent.putExtra(SV_KEY, sveWord);
    }

    public void enPressed(View view) {
        setLanguage("en");
        Intent intent = new Intent(this, Game.class);
        intent.putExtra(EN_KEY, engWord);
    }

    public void darkMode(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

    public void LightMode(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
