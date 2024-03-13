package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class Scramble extends AppCompatActivity {
    private Button submitButton;
    private TextView scrambledWordTextView;
    private EditText userInputEditText;
    private TextView resultTextView;

    private String[] words = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
    private String[] scrambledWords = {"pplea", "nnaaab", "rycher", "taed", "derrylbe", "gif", "prage"};

    private String correctWord;
    private String scrambledWord;

    public Scramble() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble);
        scrambledWordTextView = findViewById(R.id.scrambledWordTextView);
        userInputEditText = findViewById(R.id.userInputEditText);
        submitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);

        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        correctWord = words[randomIndex];
        scrambledWord = scrambledWords[randomIndex];

        scrambledWordTextView.setText(scrambledWord);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = userInputEditText.getText().toString();
                if(userInput.equals(correctWord)){
                    resultTextView.setText("Correct!");
                    userInputEditText.getText().clear();
                    int randomIndex = random.nextInt(words.length);
                    correctWord = words[randomIndex];
                    scrambledWord = scrambledWords[randomIndex];
                    scrambledWordTextView.setText(scrambledWord);
                }else{
                    resultTextView.setText("Incorrect, try again.");
                }
            }
        });
    }
}