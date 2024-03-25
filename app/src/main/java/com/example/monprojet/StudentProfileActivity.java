package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StudentProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.tool_bar);

        toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_profile_notification) {
                Toast.makeText(StudentProfileActivity.this, "Vous avez cliquez sur l'icon notification", Toast.LENGTH_SHORT).show();
                return true;
            }
            else {
                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(StudentProfileActivity.this, AccueilActivity.class));
                        break;
                    case R.id.action_courses:
                        startActivity(new Intent(StudentProfileActivity.this, CoursesActivity.class));
                        break;
                    case R.id.action_chat:
                        startActivity(new Intent(StudentProfileActivity.this, MessageActivity.class));
                        break;
                    case R.id.action_profile:
                        startActivity(new Intent(StudentProfileActivity.this, StudentProfileActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}