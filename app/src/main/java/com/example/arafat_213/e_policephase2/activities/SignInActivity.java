package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static int SPLASH_TIME_OUT = 2000;
    EditText usernameET;
    EditText passwordET;
    Button signInBTN;
    ProgressBar signInPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    public void init() {
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        signInPB = findViewById(R.id.signInPB);
        signInBTN = findViewById(R.id.signInBTN);
        signInBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInBTN:
                signInPB.setVisibility(View.VISIBLE);
                final String username = usernameET.getText().toString();
                final String password = passwordET.getText().toString();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (signIn(username, password)) {
                            Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Toast.makeText(
                                    SignInActivity.this,
                                    "Welcome aboard, Chief!",
                                    Toast.LENGTH_LONG
                            ).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(
                                    SignInActivity.this,
                                    "Invalid username/password",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }, SPLASH_TIME_OUT);

                break;
        }
    }

    public boolean signIn(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
