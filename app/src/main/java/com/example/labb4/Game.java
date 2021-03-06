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

import static com.example.labb4.MainActivity.*;

public class Game extends AppCompatActivity {

    TextView wordToGuess_textView;
    TextView lives_textView;
    TextView letterGuessed_textView;
    EditText userInput_editText;
    Button guess_button;
    ImageView hangmanImage;




    private static final String SH_KEY = "MY_SAVE_FILE";
    private static final String STATE_KEY = "MY_STATE_KEY";
    private static final String WON_MSG = "YOU WON!";
    private static final String LOSE_MSG = "GAME OVER";


    private String aWord;
    private String aWordString;
    private String userInput;
    private int livesRecorder = 7;


    private ArrayList<String> aWordsListArray;
    private ArrayList<String> recordLetterList;
    private char[] aWordCharList;
    private char[] answerCharList;



    public void randomWordToGuess() {
        Intent intent = getIntent();
        String SV = intent.getStringExtra(LANGUAGE_KEY_SV);
        String EN = intent.getStringExtra(LANGUAGE_KEY_EN);
        aWordsListArray = new ArrayList<>();
        //aWordsListArray.add("english");
        //aWordsListArray.add("svenska");
        aWordsListArray.add(SV);
        aWordsListArray.add(EN);
        // get a random word from Array and add it to aWord
        //aWord = aWordsListArray.get((int) (Math.random() * aWordsListArray.size()));
        aWord = EN;
        aWord = SV;

        // add aWord to aWordCharList to convert it.
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
                    userInput_editText.setError("Too long");
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

    // method to record user input and added to array.
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

    // send MSG data to next end activity.
    public void endIntent(String result){
        Intent intent = new Intent(this, End.class);
        intent.putExtra("END",result);
        intent.putExtra("aWord","The Word is: "+aWord);
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
                    userInput_editText.setError("Not found");
                    stop = false;
                }
            }
        }
        if (!stop) livesRecorder--;
        updateImg(livesRecorder);
        if (livesRecorder < 1) endIntent(LOSE_MSG);
            //wordToGuess_textView.setText("GAME OVER");
    }

    // get image from internet github.
    private void updateImg(int number) {
        Glide.with(this).load("https://raw.githubusercontent.com/atlalbasha/Labb4/master/app/src/main/res/drawable-v24/hangman"+number+".png").into(hangmanImage);

       /* int resImg = getResources().getIdentifier("hangman" + number, "drawable",
                getPackageName());
        hangmanImage.setImageResource(resImg);

        */
    }


  /*  public void saveState(){
        String state = wordToGuess_textView.getText().toString();
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(STATE_KEY, state);
        editor.apply();
    }
    public void readState(){
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        String state = sh.getString(STATE_KEY,"");
        wordToGuess_textView.setText(state);

    }

   */



}


