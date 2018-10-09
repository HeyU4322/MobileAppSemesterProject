package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Button saveSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveSettingsButton = (Button) findViewById(R.id.saveSettingsButton);

        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToListOfTaskLists = new Intent(Settings.this, ListOfTaskLists.class);
                startActivity(goToListOfTaskLists);
            }
        });
    }
}
