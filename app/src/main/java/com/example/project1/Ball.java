package com.example.project1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Ball extends AppCompatActivity {
    private ImageView ball;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);

        ball = findViewById(R.id.ball);
        constraintLayout = findViewById(R.id.constraintLayout);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            float xMax = constraintLayout.getWidth() - ball.getWidth();
            float yIncrement = ball.getHeight(); // You might need to adjust this value

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(ball, "x", 0, xMax);
            animatorX.setDuration(2000);
            animatorX.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    float nextY = ball.getY() + yIncrement;
                    if (nextY + ball.getHeight() > constraintLayout.getHeight()) {
                        // We've reached the bottom of the screen, reset
                        ball.setY(0);
                    } else {
                        ball.setY(nextY);
                    }
                    // Restart the animation
                    animatorX.start();
                }
            });
            animatorX.start();
        }

   }



}