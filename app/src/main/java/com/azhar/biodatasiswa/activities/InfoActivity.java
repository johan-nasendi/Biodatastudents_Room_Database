package com.azhar.biodatasiswa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.azhar.biodatasiswa.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        bottomNavigationView = findViewById(R.id.button_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_info);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext()
                                ,AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_info:
                        return true;

                    case R.id.nav_setting:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }
}