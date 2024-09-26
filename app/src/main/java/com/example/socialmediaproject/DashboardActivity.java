package com.example.socialmediaproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String myuid;
    ActionBar actionBar;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Home");  // Đặt tiêu đề mặc định là "Home"
        } else {
            // Xử lý khi ActionBar là null, có thể log hoặc thông báo
            // Log.e("DashboardActivity", "ActionBar is null");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        // Khởi tạo fragment mặc định
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment, "");
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            String title = "";

            if (menuItem.getItemId() == R.id.nav_home) {
                title = "Home";
                fragment = new HomeFragment();
            } else if (menuItem.getItemId() == R.id.nav_profile) {
                title = "Profile";
                fragment = new ProfileFragment();
            } else if (menuItem.getItemId() == R.id.nav_users) {
                title = "Users";
                fragment = new UsersFragment();
            } else if (menuItem.getItemId() == R.id.nav_chat) {
                title = "Chats";
                fragment = new ChatListFragment();
            } else if (menuItem.getItemId() == R.id.nav_addblogs) {
                title = "Add Blogs";
                fragment = new AddBlogsFragment();
            }

            if (fragment != null) {
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                return true;
            }

            return false;
        }
    };
}
