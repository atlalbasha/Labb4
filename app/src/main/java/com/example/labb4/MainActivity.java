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

    Button enButton;
    Button svButton;

    public static String LANGUAGE_KEY_SV;
    public static String LANGUAGE_KEY_EN;
    private boolean EN_KEY;
    private boolean SV_KEY;

    // i keep too many comment methods/ code in program to help me later when i want to to use some of them..

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

        enButton = findViewById(R.id.en_button);
        svButton = findViewById(R.id.sv_button);


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
        if (EN_KEY) {
            intent.putExtra(LANGUAGE_KEY_EN,"english");
        }else {
            intent.putExtra(LANGUAGE_KEY_SV,"svenska");
        }
        if (SV_KEY) {
            intent.putExtra(LANGUAGE_KEY_SV,"svenska");

        }else {
            intent.putExtra(LANGUAGE_KEY_EN,"english");
        }
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

    // change language method.
    public void setLanguage(String enSV) {
        String languageToLoad = enSV; // enSV type of language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);
    }
    // change language button pressed.
    public void svPressed(View view) {
        setLanguage("sv");
        SV_KEY = true;
    }
    public void enPressed(View view) {
        setLanguage("en");
        EN_KEY = true;
    }

    // dark and light mode.
    public void darkMode(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
    public void LightMode(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}
