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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpScreen extends AppCompatActivity {

    Button createAccountButton;
    EditText editTextPassword, editTextEmail, editTextPasswordConfirm;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account_screen);

        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        editTextPassword = (EditText) findViewById(R.id.passwordCreate);
        editTextPasswordConfirm = (EditText) findViewById(R.id.passwordConfirm);
        editTextEmail = (EditText) findViewById(R.id.emailCreate);

        mAuth = FirebaseAuth.getInstance();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordconfirm = editTextPasswordConfirm.getText().toString().trim();

        //Check all Conditions before passing
        if(email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6) {
            editTextPassword.setError("Password must be longer than 5 characters");
            editTextPassword.requestFocus();
            return;
        }

        if(!password.equals(passwordconfirm)){
            editTextPassword.setError("Passwords Don't Match");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent goToLoginScreen = new Intent(SignUpScreen.this, LoginScreen.class);
                    startActivity(goToLoginScreen);
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Already Exist", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
