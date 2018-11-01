package com.example.kalea.mobileappsemesterproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class CreateNewAccountScreen extends AppCompatActivity {

    Button createAccountButton;
    EditText passwordCreate, emailCreate;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account_screen);

        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        passwordCreate = (EditText) findViewById(R.id.passwordCreate);
        emailCreate = (EditText) findViewById(R.id.emailCreate);

        mAuth = FirebaseAuth.getInstance();


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginScreen = new Intent(CreateNewAccountScreen.this, LoginScreen.class);
                startActivity(goToLoginScreen);
            }
        });
    }

    private void registerUser() {
        String email = emailCreate.getText().toString().trim();
        String password = passwordCreate.getText().toString().trim();

        //Check all Conditions before passing
        if(email.isEmpty()) {
            emailCreate.setError("Email is required");
            emailCreate.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailCreate.setError("Please enter a valid email");
            emailCreate.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordCreate.setError("Password is required");
            passwordCreate.requestFocus();
            return;
        }
        if(password.length()<6) {
            passwordCreate.setError("Password must be longer than 5 characters");
            passwordCreate.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent goToLoginScreen = new Intent(CreateNewAccountScreen.this, LoginScreen.class);
                    startActivity(goToLoginScreen);
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Already Exist", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
