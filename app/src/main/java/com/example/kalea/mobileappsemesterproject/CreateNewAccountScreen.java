package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateNewAccountScreen extends AppCompatActivity {

    Button createAccountButton;
    EditText passwordCreate, emailCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account_screen);

        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        passwordCreate = (EditText) findViewById(R.id.passwordCreate);
        emailCreate = (EditText) findViewById(R.id.emailCreate);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginScreen = new Intent(CreateNewAccountScreen.this, LoginScreen.class);
                startActivity(goToLoginScreen);
            }
        });
    }
}
