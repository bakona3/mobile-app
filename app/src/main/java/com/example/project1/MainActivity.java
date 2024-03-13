package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   FirebaseAuth auth;
   TextView textView;
   FirebaseUser user;
   Button button, button1,button2,button3,button4,button5,button6,button7,button9;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.logout);
        button1= findViewById(R.id.seasons);
        button2= findViewById(R.id.calendar);
        button3= findViewById(R.id.scramble);
        button4= findViewById(R.id.clock);
        button5= findViewById(R.id.directions);
        button6= findViewById(R.id.multiplication);
        button7= findViewById(R.id.ball);
        button9= findViewById(R.id.numberRemember);

        textView=findViewById(R.id.user_details);
        user =auth.getCurrentUser();
        if(user==null){
            Intent intent= new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    });

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Seasons.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scramble.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Clock.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Directions.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Multiplication.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ball.class);
                startActivity(intent);
            }
        });


        button9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumberRemember.class);
                startActivity(intent);
            }
        });

        Button translateButton = findViewById(R.id.translate_button);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("tr");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.setLocale(locale);
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();  // Restart the activity
            }
        });


    }
}