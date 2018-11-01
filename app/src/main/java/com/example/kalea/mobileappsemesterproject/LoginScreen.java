package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginScreen extends AppCompatActivity {

    Button loginButton, createAccountButton;
    EditText emailLogin, passwordLogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();

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

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    private void signIn() {

        String email = emailLogin.getText().toString().trim();
        String password = passwordLogin.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent goToListMain = new Intent(LoginScreen.this, ListOfTaskLists.class);
                    startActivity(goToListMain);
                    Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Username and Password are Incorrect", Toast.LENGTH_SHORT).show();

                }

                // ...
            }
        });
    }
}
