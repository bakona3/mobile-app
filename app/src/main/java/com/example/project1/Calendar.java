package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {
    private static final String CURRENT_DAY = "CURRENT_DAY";
    private static final String CURRENT_MONTH = "CURRENT_MONTH";
    private String[] days = {"1. Monday", "2. Tuesday", "3. Wednesday", "4. Thursday", "5. Friday", "6. Saturday", "7. Sunday"};
    private String[] months = {"1. January", "2. February", "3. March", "4. April", "5.  May", "6. June", "7. July", "8. August", "9. September", "10. October", "11. November", "12. December"};
    private int currentDay = 0;
    private int currentMonth = 0;
    private TextView textView;
    private TextView textView1;
    private Button dayButton;
    private Button monthButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        textView = findViewById(R.id.textView2);
        dayButton = findViewById(R.id.dayButton2);
        textView1 = findViewById(R.id.textView3);
        monthButton = findViewById(R.id.monthButton3);
        if(savedInstanceState != null){
            currentDay = savedInstanceState.getInt(CURRENT_DAY, 0);
            textView.setText(days[currentDay]);
            currentMonth = savedInstanceState.getInt(CURRENT_MONTH, 0);
            textView.setText(months[currentMonth]);


        }
        textView.setText(days[currentDay]);
        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDay = (currentDay + 1) % days.length;
                textView.setText(days[currentDay]);
            }
        });
        textView1.setText(months[currentMonth]);
        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonth = (currentMonth + 1) % months.length;
                textView1.setText(months[currentMonth]);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_DAY, currentDay);
        outState.putInt(CURRENT_MONTH, currentMonth);
    }


}