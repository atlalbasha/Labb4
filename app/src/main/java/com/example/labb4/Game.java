package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    TextView wordToGuess_textView;
    TextView lives_textView;
    TextView letterGuessed_textView;
    EditText userInput_editText;
    Button guess_button;
    ImageView hangmanImage;

    private static final String SH_KEY = "MY_SAVE_FILE";
    private static final String STATE_KEY = "MY_STATE_KEY";
     String WON_MSG = "YOU WON!";
     String LOSE_MSG = "GAME OVER";


    String aWord;
    String aWordString;
    String userInput;
    int livesRecorder = 7;

    String engWord;
    String sveWord;

    ArrayList<String> aWordsListArray;
    char[] aWordCharList;
    char[] answerCharList;
    ArrayList<String> recordLetterList;


    public void randomWordToGuess() {
        aWordsListArray = new ArrayList<>();
        aWordsListArray.add("atlal");
        aWordsListArray.add("basha");

        // get a random word from Array and add it to aWord
        aWord = aWordsListArray.get((int) (Math.random() * aWordsListArray.size()));

        // add aWord to aWordCharList to convert it to word to char word.
        aWordCharList = aWord.toCharArray();
        // create new char array = aWordCharList length
        answerCharList = new char[aWordCharList.length];
        for (int i = 0; i < answerCharList.length; i++) {
            answerCharList[i] = '_';
        }
    }




    public void printUnderscore() {
        for (int i = 0; i < answerCharList.length; i++) {
            if (answerCharList[i] == '_') {
                wordToGuess_textView.append(" _");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //toolbar Icon
        ImageView menuBarIcon = findViewById(R.id.toolBar_menu_icon);
        ImageView infoIcon = findViewById(R.id.toolBar_info_icon);
        ImageView playIcon = findViewById(R.id.toolBar_game_icon);
        TextView textBarIcon = findViewById(R.id.toolBar_textView_icon);
        textBarIcon.setText(R.string.game);
        //toolbar click on icon
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

        //game
        wordToGuess_textView = findViewById(R.id.word_to_guees);
        lives_textView = findViewById(R.id.lives);
        lives_textView.setText(getString(R.string.liv)+livesRecorder);
        userInput_editText = findViewById(R.id.editText);
        guess_button = findViewById(R.id.guess_button);
        letterGuessed_textView = findViewById(R.id.letter_textView);
        hangmanImage = findViewById(R.id.hangman_imageView);

        Intent intent = getIntent();
        engWord = intent.getStringExtra("EN_KEY");
        sveWord = intent.getStringExtra("SV_KEY");




        //Picasso.get().load(R.drawable.hangman7).into(hangmanImage);
        //readState();

        //Edit text check
        userInput_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userInput = userInput_editText.getText().toString().trim();
                guess_button.setEnabled(!userInput.isEmpty());
                if (userInput.length() > 1) {
                    guess_button.setEnabled(false);
                    userInput_editText.setError("To long");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // guess button pressed
        guess_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //revealLetterInWord(userInput);
                revealLetter(userInput);
                lives_textView.setText("lives: " + livesRecorder);
                letterGuessed_textView.append(userInput + ", ");
                recordLetter();
                //saveState();
            }
        });



        randomWordToGuess();
        printUnderscore();
    }


    public String getUserInput() {
        userInput = userInput_editText.getText().toString();
        return userInput;
    }

    public void recordLetter() {
        recordLetterList = new ArrayList<>();
        recordLetterList.add(userInput);
    }

    //Intent activity method
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


    public void endIntent(String result){
        Intent intent = new Intent(this, End.class);
        intent.putExtra("END",result);
        intent.putExtra("aWord","The Word was: "+aWord);
        intent.putExtra("lives",""+livesRecorder);


        startActivity(intent);
    }

    void revealLetter(String userInput) {
        boolean next = true;
        for (int i = 0; i < aWordCharList.length; i++) {
             if (userInput.charAt(0) == aWordCharList[i]) {
                 answerCharList[i] = aWordCharList[i];
                String changeLetter = " ";
                for (char c : answerCharList) {
                    changeLetter += c + " ";
                }
                wordToGuess_textView.setText(changeLetter);
                aWordString = String.valueOf(answerCharList);
                if (aWordString.equals(aWord)) endIntent(WON_MSG);
                    //wordToGuess_textView.setText("YOU WON");
                 next = false;
             }
        }
        boolean stop = true;
        if (next) {
            for (int i = 0; i < aWordCharList.length; i++) {
                if (userInput.charAt(0) != aWordCharList[i]) {
                    stop = false;
                }
            }
        }
        if (!stop) livesRecorder--;
        updateImg(livesRecorder);
        if (livesRecorder < 1) endIntent(LOSE_MSG);
            //wordToGuess_textView.setText("GAME OVER");
    }

    private void updateImg(int number) {
        Glide.with(this).load("https://raw.githubusercontent.com/atlalbasha/Labb4/master/app/src/main/res/drawable-v24/hangman"+number+".png").into(hangmanImage);

        //int resImg = getResources().getIdentifier("hangman" + number, "drawable",
            //    getPackageName());
       // hangmanImage.setImageResource(resImg);
    }


  /*  void saveState(){
        String state = wordToGuess_textView.getText().toString();
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(STATE_KEY, state);
        editor.apply();
    }
    void readState(){
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        String state = sh.getString(STATE_KEY,"");
        wordToGuess_textView.setText(state);

    }

   */

}


