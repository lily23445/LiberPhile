package com.example.liberphile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private LinearLayout linearLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        linearLayout = findViewById(R.id.linearLayout); // Ensure this ID is in your XML

        buttonLogin.setOnClickListener(v -> login());

        textViewSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Set up the click listener for the LinearLayout
        linearLayout.setOnClickListener(v -> {
            // Here, you might want to check if login is successful before starting the homepage activity
            Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
            startActivity(intent);
        });
    }


    private void login() {
        // Implement login logic here
        // For demonstration purposes, navigate to HomepageActivity
        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}
