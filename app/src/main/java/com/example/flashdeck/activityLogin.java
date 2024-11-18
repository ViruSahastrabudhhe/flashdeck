package com.example.flashdeck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityLogin extends AppCompatActivity {

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activityHomepage.class);
                startActivity(intent);
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
}