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

public class ListOfTaskLists extends AppCompatActivity {
    private SharedPreferences savedInfo;
    private EditText newTaskList;
    private TableLayout tableLayout;
    private static final String SAVED_INFO_NAME = "TaskListsInfo";
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_task_lists);

        savedInfo = getSharedPreferences(SAVED_INFO_NAME, MODE_PRIVATE);

        tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        newTaskList = (EditText)findViewById(R.id.newTaskList);

        refreshButton("",true);

        logoutButton = (Button) findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(ListOfTaskLists.this, LoginScreen.class);
                startActivity(logout);
            }
        });

    }
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.addTaskList:
                if(newTaskList.getText().length() > 0) {
                    String taskList = newTaskList.getText().toString();
                    boolean tagAlreadySaved = savedInfo.contains(taskList);
                    SharedPreferences.Editor myEditor = savedInfo.edit();
                    myEditor.putString(taskList, taskList);
                    myEditor.apply();
                    if (!tagAlreadySaved) {
                        refreshButton(taskList, false);
                    }
                    newTaskList.setText("");
                }
                else{
                    AlertDialog.Builder bld = new AlertDialog.Builder(ListOfTaskLists.this);
                    bld.setTitle("Missing Text");
                    bld.setMessage("Please enter your new task list name.");
                    bld.setPositiveButton("OK", null);
                    AlertDialog missingDialog = bld.create();
                    missingDialog.show();
                }
                break;
            case R.id.clearTaskLists:
                AlertDialog.Builder adb = new AlertDialog.Builder(ListOfTaskLists.this);
                adb.setTitle("Are you sure?");
                adb.setMessage("This will delete all saved task lists.");
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
        Button taskListBTN = (Button)row.findViewById(R.id.newTaskBTN);
        taskListBTN.setText(tag);
        tableLayout.addView(row,index);
    }
}

