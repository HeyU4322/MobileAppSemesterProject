package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListOfTaskLists extends AppCompatActivity {
    Button logoutButton, settingsButton, taskListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_task_lists);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        taskListButton = (Button) findViewById(R.id.taskListButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(ListOfTaskLists.this, LoginScreen.class);
                startActivity(logout);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(ListOfTaskLists.this, Settings.class);
                startActivity(settings);
            }
        });

        taskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskList = new Intent(ListOfTaskLists.this, TaskList.class);
                startActivity(goToTaskList);
            }
        });

    }
}
