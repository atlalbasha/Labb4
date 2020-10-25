package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class End extends AppCompatActivity {

    TextView result;
    TextView wordResult;
    TextView livesResult;

    Button backToMenu;

    String MSG;
    String livesEnd;
    String aWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        //Toolbar Icon.
        ImageView menuBarIcon = findViewById(R.id.toolBar_menu_icon);
        ImageView infoIcon = findViewById(R.id.toolBar_info_icon);
        ImageView playIcon = findViewById(R.id.toolBar_game_icon);
        TextView textBarIcon = findViewById(R.id.toolBar_textView_icon);
        textBarIcon.setText(R.string.game);

        //Toolbar clicked  Icon.
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


        //End Activity.
        backToMenu = findViewById(R.id.back_to_menu_button);
        result = findViewById(R.id.result_textView);
        wordResult = findViewById(R.id.wordFound_textView);
        livesResult = findViewById(R.id.lives_result_textView);

        // Get Data from Game Activity.
        Intent intent = getIntent();
        MSG = intent.getStringExtra("END");
        result.setText(MSG);

        livesEnd = intent.getStringExtra("lives");
        livesResult.setText("Lives is: "+livesEnd);

        aWord = intent.getStringExtra("aWord");
        wordResult.setText(aWord);
    }

    //Intent Activity Method.
    public void gameIntent() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    public void aboutIntent() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
    public void mainIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void backToMenu(View view) {
        mainIntent();
    }
    @Override
    public void onBackPressed() {
        mainIntent();
    }


}