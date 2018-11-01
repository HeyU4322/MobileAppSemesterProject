package com.example.kalea.mobileappsemesterproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static android.R.attr.onClick;

public class TaskList extends AppCompatActivity {

    private SharedPreferences savedInfo;
    private EditText newTask;
    private TableLayout tableLayout;
    private static final String SAVED_INFO_NAME = "TaskListInfo";
    private Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        location = (Button) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLocation = new Intent(TaskList.this, GPS.class);
                startActivity(goToLocation);
            }
        });

        savedInfo = getSharedPreferences(SAVED_INFO_NAME, MODE_PRIVATE);

        tableLayout = (TableLayout)findViewById(R.id.tableLayout);

        newTask = (EditText) findViewById(R.id.newTask);

        refreshButton("",true);
    }

    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.addTask:
                if(newTask.getText().length() > 0) {
                    String task = newTask.getText().toString();
                    boolean taskListAlreadySaved = savedInfo.contains(task);
                    SharedPreferences.Editor myEditor = savedInfo.edit();
                    myEditor.putString(task, task);
                    myEditor.apply();
                    if (!taskListAlreadySaved) {
                        refreshButton(task, false);
                    }
                    newTask.setText("");
                }
                else{
                    AlertDialog.Builder bld = new AlertDialog.Builder(TaskList.this);
                    bld.setTitle("Missing Text");
                    bld.setMessage("Please enter your new task.");
                    bld.setPositiveButton("OK", null);
                    AlertDialog missingDialog = bld.create();
                    missingDialog.show();
                }
                break;
            case R.id.clearTaskList:
                AlertDialog.Builder adb = new AlertDialog.Builder(TaskList.this);
                adb.setTitle("Are you sure?");
                adb.setMessage("This will delete all saved tasks.");
                adb.setCancelable(true);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tableLayout.removeAllViews();
                        SharedPreferences.Editor myEditor = savedInfo.edit();
                        myEditor.clear();
                        myEditor.apply();
                    }
                });
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
                break;
        }

    }

    private void refreshButton(String tag, boolean applytoAll) {
        Map<String,?> queryMap = savedInfo.getAll();
        Set<String> tagSet = queryMap.keySet();
        String[] tags = tagSet.toArray(new String[0]);
        Arrays.sort(tags,String.CASE_INSENSITIVE_ORDER);
        int index = Arrays.binarySearch(tags, tag);
        if(applytoAll){
            for(int i = 0; i < tags.length; i++ ) {
                makeTagGUI(tags[i], i);
            }
        }
        else
            makeTagGUI(tag, index);
    }

    private void makeTagGUI(String tag, int index) {
        LayoutInflater li =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = li.inflate(R.layout.new_tag_view, null);
        Button taskBTN = (Button)row.findViewById(R.id.newTaskBTN);
        taskBTN.setText(tag);
        tableLayout.addView(row,index);
    }
}