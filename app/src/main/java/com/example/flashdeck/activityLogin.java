package com.example.flashdeck;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityLogin extends AppCompatActivity {

    public EditText inputEmail, inputPassword;
    public DBHandler dbHandler;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnSignUp();
        btnForgotPassword();
        btnSignIn();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnSignIn() {
        Button btn = (Button)findViewById(R.id.login_btnSignIn);
        inputEmail = findViewById(R.id.login_txtEmail);
        inputPassword = findViewById(R.id.login_txtPassword);

        dbHandler = new DBHandler(activityLogin.this);
        db = dbHandler.getWritableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryEmail = inputEmail.getText().toString();
                String queryPassword = inputPassword.getText().toString();

                if (queryEmail.isEmpty() && queryPassword.isEmpty()) {
                    Toast.makeText(activityLogin.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmailFormatInvalid(queryEmail)) {
                    Toast.makeText(activityLogin.this, "Email format is not valid! Must contain domain!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmailNotLongEnough(queryEmail)) {
                    Toast.makeText(activityLogin.this, "Email is not long enough!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryPassword.isEmpty()) {
                    Toast.makeText(activityLogin.this, "Please input your password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryEmail.isEmpty()) {
                    Toast.makeText(activityLogin.this, "Please input your email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(dbHandler.selectUserEmail(queryEmail))) {
                    Toast.makeText(activityLogin.this, "Account does not exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(dbHandler.selectUser(queryEmail, queryPassword))) {
                    Toast.makeText(activityLogin.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHandler.selectUser(queryEmail, queryPassword)) {
                    Toast.makeText(activityLogin.this, "Successfully signed in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activityLogin.this, activityHomepage.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void btnForgotPassword() {
        Button btn = (Button)findViewById(R.id.login_btnForgotPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activityForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    public void btnSignUp() {
        Button btn = (Button)findViewById(R.id.login_btnSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activitySignUp.class);
                startActivity(intent);
            }
        });
    }

    public boolean isEmailFormatInvalid(String email) {
        return !email.contains("@") || !email.contains(".com");
    }

    public boolean isEmailNotLongEnough(String email) {
        int domain = email.indexOf("@");
        String substring = email.substring(0, domain);
        return substring.length() < 6;
    }
}