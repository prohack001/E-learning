package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<String> stringArrayList;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.tool_bar);
        recyclerView = findViewById(R.id.recyclerView);

        toolbar.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.menuNotification) {
                    Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon notification", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if(itemId == R.id.menuSearch) {
                    Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon recherche", Toast.LENGTH_SHORT).show();
                    return true;
                }
//                else if(itemId == R.id.menuSettings) {
//                    Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon paramètres", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
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
                        Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_courses:
                        Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon mes cours", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_chat:
                        Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(WelcomeActivity.this, "Vous avez cliquez sur l'icon profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        stringArrayList = new ArrayList<>();
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");

        recyclerViewAdapter = new RecyclerViewAdapter(this, stringArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}