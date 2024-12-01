package com.example.flashdeck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activitySignUp extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputConfirmPassword;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        btnBackToLogin();
        btnCreateAccount();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnCreateAccount() {
        Button btn = findViewById(R.id.signUp_btnSignUp);
        inputEmail = findViewById(R.id.signUp_txtEmail);
        inputPassword = findViewById(R.id.signUp_txtPassword);
        inputConfirmPassword = findViewById(R.id.signUp_txtConfirmPassword);

        dbHandler = new DBHandler(activitySignUp.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryEmail = inputEmail.getText().toString();
                String queryPassword = inputPassword.getText().toString();
                String queryConfirmPassword = inputConfirmPassword.getText().toString();

                if (queryEmail.isEmpty() && queryPassword.isEmpty() && queryConfirmPassword.isEmpty() ) {
                    Toast.makeText(activitySignUp.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmailFormatInvalid(queryEmail)) {
                    Toast.makeText(activitySignUp.this, "Email format is not valid! Must contain domain!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmailNotLongEnough(queryEmail)) {
                    Toast.makeText(activitySignUp.this, "Email is not long enough!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryPassword.length() < 8) {
                    Toast.makeText(activitySignUp.this, "Password must be longer than 8 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(queryPassword.equals(queryConfirmPassword))) {
                    Toast.makeText(activitySignUp.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHandler.selectUserEmail(queryEmail)) {
                    Toast.makeText(activitySignUp.this, "Account already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewUser(queryEmail, queryPassword);

                Toast.makeText(activitySignUp.this, "Account successfully signed up!", Toast.LENGTH_SHORT).show();
                inputEmail.setText("");
                inputPassword.setText("");
                inputConfirmPassword.setText("");
                Intent intent = new Intent(activitySignUp.this, activityLogin.class);
                startActivity(intent);
            }
        });
    }

    public void btnBackToLogin() {
        Button btn = (Button)findViewById(R.id.signUp_btnBackToLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activitySignUp.this, activityLogin.class);
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