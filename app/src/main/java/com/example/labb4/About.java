package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView menuBarIcon = findViewById(R.id.toolBar_menu_icon);
        ImageView infoIcon = findViewById(R.id.toolBar_info_icon);
        ImageView playIcon = findViewById(R.id.toolBar_game_icon);
        TextView textBarIcon = findViewById(R.id.toolBar_textView_icon);
        textBarIcon.setText(R.string.about);

        ImageView imageView = findViewById(R.id.about_imageView);

        String url = "https://lh3.googleusercontent.com/a-/AOh14Gh8RjVNSprmURVgo16F5zqzFVOcx68hXdMO7EDx=s192-c-rg-br100";

        Picasso.get().load(url).into(imageView);


        //Glide.with(this).load("https://raw.githubusercontent.com/atlalbasha/Labb4/master/app/src/main/res/drawable-v24/hangman1.png").into(imageView);



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