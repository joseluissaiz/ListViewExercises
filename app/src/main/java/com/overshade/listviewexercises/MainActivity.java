package com.overshade.listviewexercises;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button loremListButton;
    Button ratingListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptViewToVariables();
        addButtonActions();
    }

    private void adaptViewToVariables() {
        loremListButton = findViewById(R.id.loremButton);
        ratingListButton = findViewById(R.id.ratingButton);
    }

    private void addButtonActions() {
        loremListButton.setOnClickListener(e -> runActivity(LoremList.class));
        ratingListButton.setOnClickListener(e -> runActivity(RatingList.class));
    }

    private void runActivity(Class<? extends Activity> activity) {
        Intent activityIntent = new Intent(getApplicationContext(), activity);
        startActivity(activityIntent);
    }

}