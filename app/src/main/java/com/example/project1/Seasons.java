package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class Seasons extends AppCompatActivity {
    private GifImageView gifImageView;
    private Button nextButton;
    private List<Integer> gifs = Arrays.asList(R.drawable.gif0, R.drawable.gif1, R.drawable.gif2, R.drawable.gif3);
    private int gifIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);


        gifImageView = findViewById(R.id.gif0);
        nextButton = findViewById(R.id.next_button);

        // Initial image
        gifImageView.setImageResource(gifs.get(gifIndex));

        nextButton.setVisibility(View.VISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifIndex++;
                if (gifIndex >= gifs.size()) {
                    gifIndex = 0;
                }
                gifImageView.setImageResource(gifs.get(gifIndex));
            }
        });
    }
}