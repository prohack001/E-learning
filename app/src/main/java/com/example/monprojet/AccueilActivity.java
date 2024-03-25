package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccueilActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    CoursesFragment coursesFragment;
    MessageFragment messageFragment;
    ProfileFragment profileFragment;
    Fragment activeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize fragments
        homeFragment = new HomeFragment();
        coursesFragment = new CoursesFragment();
        messageFragment = new MessageFragment();
        profileFragment = new ProfileFragment();

        // Add all fragments to the container
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_welcome, profileFragment, "4").hide(profileFragment)
                .add(R.id.frame_layout_welcome, messageFragment, "3").hide(messageFragment)
                .add(R.id.frame_layout_welcome, coursesFragment, "2").hide(coursesFragment)
                .add(R.id.frame_layout_welcome, homeFragment, "1")
                .commit();

        activeFragment = homeFragment;

//        toolbar = findViewById(R.id.tool_bar);
//        recyclerView = findViewById(R.id.recyclerView);


//        toolbar.setOnMenuItemClickListener(item -> {
//            int itemId = item.getItemId();
//            if (itemId == R.id.menuNotification) {
//                Toast.makeText(AccueilActivity.this, "Vous avez cliquez sur l'icon notification", Toast.LENGTH_SHORT).show();
//                return true;
//            } else if (itemId == R.id.menuSearch) {
//                Toast.makeText(AccueilActivity.this, "Vous avez cliquez sur l'icon recherche", Toast.LENGTH_SHORT).show();
//                return true;
//            } else if (itemId == R.id.menuSettings) {
//                Toast.makeText(AccueilActivity.this, "Vous avez cliquez sur l'icon paramètres", Toast.LENGTH_SHORT).show();
//                return true;
//            } else {
//                return false;
//            }
//        });

//        bottomNavigationView.setSelectedItemId(R.id.action_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        moveToFragment(homeFragment);
                        break;
                    case R.id.action_courses:
                        moveToFragment(coursesFragment);
                        break;
                    case R.id.action_chat:
                        moveToFragment(messageFragment);
                        break;
                    case R.id.action_profile:
                        moveToFragment(profileFragment);
                        break;
                }
                return true;
            }
        });

//        stringArrayList = new ArrayList<>();
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//        stringArrayList.add("https://wtop.com/wp-content/uploads/2020/06/HEALTHYFRESH.jpg");
//
//        recyclerViewAdapter = new RecyclerViewAdapter(this, stringArrayList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void moveToFragment(Fragment fragment){
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_welcome, fragment).commit();
        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(fragment).commit();
        activeFragment = fragment;
    }

    public void switchToHomeFragment() {
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}