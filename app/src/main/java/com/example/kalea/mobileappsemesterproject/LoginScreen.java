package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    Button loginButton, createAccountButton;
    EditText emailLogin, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButton = (Button) findViewById(R.id.loginButton);
        createAccountButton = (Button)findViewById(R.id.createAccountButton);
        emailLogin = (EditText) findViewById(R.id.emailLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        final String emailCorrect = "heyYou@gmail.com";
        final String passwordCorrect = "heyYou";

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateNewAccountScreen = new Intent(LoginScreen.this, CreateNewAccountScreen.class);
                startActivity(goToCreateNewAccountScreen);
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToListOfTaskLists = new Intent(LoginScreen.this, ListOfTaskLists.class);
                if(emailLogin.getText().toString().equals(emailCorrect) &&
                        passwordLogin.getText().toString().equals(passwordCorrect))
                startActivity(goToListOfTaskLists);
            }
        });
    }
}
