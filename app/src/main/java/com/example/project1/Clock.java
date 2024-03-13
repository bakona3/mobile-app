package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Clock extends AppCompatActivity {
    private ImageView clockImage;
    private TextView scoreText;
    private Button[] options;
    private Button nextButton;
    private int score;
    private int correctOptionId;
    private int guesses;
    private int currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        // Initialize components
        clockImage = findViewById(R.id.imageView4);
        scoreText = findViewById(R.id.score);

        options = new Button[4];
        options[0] = findViewById(R.id.button);
        options[1] = findViewById(R.id.button1);
        options[2] = findViewById(R.id.button2);
        options[3] = findViewById(R.id.button3);

        nextButton = findViewById(R.id.next_button);

        score = 0;
        guesses = 0;
        currentQuestion = 0;

        loadQuestion();

        // Set up click listeners for options
        for (Button option : options) {
            option.setOnClickListener(v -> {
                guesses++;
                if (guesses <= 3) { // Allow only 3 guesses
                    if (v.getId() == correctOptionId) {
                        // Correct answer
                        score++;
                        Toast.makeText(getApplicationContext(),"Correct!", Toast.LENGTH_SHORT).show();
                        // Show Next button
                        nextButton.setVisibility(View.VISIBLE);
                    } else {
                        // Incorrect answer
                        Toast.makeText(getApplicationContext(),"Incorrect. Try again.", Toast.LENGTH_SHORT).show();
                    }
                    // Update score
                    scoreText.setText("Score: " + score);
                } else {
                    Toast.makeText(getApplicationContext(),"No more guesses. Click Next.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Set up click listener for Next button
        nextButton.setOnClickListener(v -> {
            // Logic for loading the next question goes here
            guesses = 0; // Reset guesses
            currentQuestion = (currentQuestion + 1) % 4; // Move to next question and cycle back to 0 after 3
            loadQuestion(); // Load the next question
            nextButton.setVisibility(View.INVISIBLE); // Hide Next button
        });
    }

    private void loadQuestion() {
        // Change image based on current question
        int id = getResources().getIdentifier("clock" + currentQuestion, "drawable", getPackageName());
        clockImage.setImageResource(id);

        // Update correct answer option based on the current question
        switch (currentQuestion) {
            case 0:
                correctOptionId = R.id.button;
                break;
            case 1:
                correctOptionId = R.id.button1;
                break;
            case 2:
                correctOptionId = R.id.button2;
                break;
            case 3:
                correctOptionId = R.id.button3;
                break;
        }
    }
}