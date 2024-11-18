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
import com.example.flashdeck.activityLogin;
import com.example.flashdeck.ui.settings.SettingsFragment;

public class activitySettingsAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_account);

        logout();
        backToSettings();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void logout() {
        Button btn = (Button) findViewById(R.id.settingsAccount_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activitySettingsAccount.this, activityLogin.class);
                startActivity(intent);
            }
        });
    }

    public void backToSettings() {
        Button btn = (Button) findViewById(R.id.settingsAccount_backToSettings);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activitySettingsAccount.this, SettingsFragment.class);
                startActivity(intent);
            }
        });
    }
}