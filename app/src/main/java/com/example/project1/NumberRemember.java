package com.example.project1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumberRemember extends AppCompatActivity {
    // Forward challenge variables
    private String digitSequence = "";
    private Random random = new Random();
    private Handler handler = new Handler();
    private TextView tvDigit;
    private EditText etInput;
    private Button btnConfirm;
    private Button btnStart;
    private int guessCount;



    // Backward challenge variables
    private String digitSequenceBack = "";
    private Handler handlerBack = new Handler();
    private TextView tvDigitBack;
    private EditText etInputBack;
    private Button btnConfirmBack;
    private Button btnStartBack;
    private int guessCountBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_remember);

        tvDigit = findViewById(R.id.tv_digit);
        etInput = findViewById(R.id.et_input);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnStart = findViewById(R.id.btn_start);

        // Initialize backward challenge controls
        tvDigitBack = findViewById(R.id.tv_digit_back);
        etInputBack = findViewById(R.id.et_input_back);
        btnConfirmBack = findViewById(R.id.btn_confirm_back);
        btnStartBack = findViewById(R.id.btn_start_back);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateSequence();
                btnStart.setEnabled(false); // Disables the start button after it's clicked
            }
        });

        btnConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerBack();
            }
        });

        btnStartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateSequenceBack();
                btnStartBack.setEnabled(false); // Disables the start button after it's clicked
            }
        });
    }


    private void generateSequence() {
        for (int i = 0; i < 5; i++) {
            digitSequence += Integer.toString(random.nextInt(10));
        }
        showSequence();
    }

    private void showSequence() {
        guessCount = 0;
        for (int i = 0; i < digitSequence.length(); i++) {
            final int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvDigit.setText(Character.toString(digitSequence.charAt(index)));
                }
            }, 1000 * i);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDigit.setText("");
            }
        }, 1000 * digitSequence.length());
    }

    private void checkAnswer() {
        if (etInput.getText().toString().equals(digitSequence)) {
            // correct answer
            tvDigit.setText("Correct!");
            guessCount++;
            etInput.setText("");
        } else {
            // wrong answer
            tvDigit.setText("Incorrect. Try again.");
            etInput.setText("");
        }

        // Proceed to the next sequence
        if (guessCount < 5) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    digitSequence = "";
                    generateSequence();
                }
            }, 3000);
        } else {
            tvDigit.setText("Game Over!");
        }
    }
//new methods for the backward part

    private void generateSequenceBack() {
        digitSequenceBack = String.valueOf(10000 + random.nextInt(90000)); // Generate a random 5-digit number
        tvDigitBack.setText(digitSequenceBack); // Display the number
        handlerBack.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDigitBack.setText(""); // Hide the number after 5 seconds
            }
        }, 5000);
    }


    private void checkAnswerBack() {
        StringBuilder reverseInput = new StringBuilder(etInputBack.getText().toString()).reverse();
        if (reverseInput.toString().equals(digitSequenceBack)) {
            tvDigitBack.setText("Correct!");
            etInputBack.setText("");
            btnStartBack.setEnabled(true);
        } else {
            tvDigitBack.setText("Incorrect. Try again.");
            etInputBack.setText("");
        }
        // Adds a delay before generating the new number
        handlerBack.postDelayed(new Runnable() {
            @Override
            public void run() {
                generateSequenceBack(); // Generates a new number after each guess
            }
        }, 2000); // 2000 ms delay
    }

}