package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TaskList extends AppCompatActivity {

    Button editLocationButton, saveTaskListButton;
    EditText taskOne, taskTwo, taskThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        editLocationButton = (Button) findViewById(R.id.editLocationButton);
        saveTaskListButton = (Button) findViewById(R.id.saveTaskListButton);

        taskOne = (EditText) findViewById(R.id.taskOne);
        taskTwo = (EditText) findViewById(R.id.taskTwo);
        taskThree = (EditText) findViewById(R.id.taskThree);

        taskOne.setText("Clean kitchen");
        taskTwo.setText("Do laundry");
        taskThree.setText("Vacuum");

        editLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGPS = new Intent(TaskList.this, GPS.class);
                startActivity(goToGPS);
            }
        });

        saveTaskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToListOfTaskLists = new Intent(TaskList.this, ListOfTaskLists.class);
                startActivity(goToListOfTaskLists);
            }
        });
    }
}
