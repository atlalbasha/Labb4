package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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


}