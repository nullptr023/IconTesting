package com.example.nullptr023.icontesting;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFragmentContainer;
    private BottomNavigationView mMainNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContainer = findViewById(R.id.main_frame);
        mMainNavView = findViewById(R.id.main_nav);

        changeFragment(new KeypadFragment(), "keypadFragment");
        mMainNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (mMainNavView.getSelectedItemId() == item.getItemId()) {
                    Log.e("check selected item", "same selected id");
                    return true;
                }

                switch (item.getItemId()) {
                    case R.id.nav_keypad:
                        Log.e("nav_keypad", "nav keypad!!!");
                        changeFragment(new KeypadFragment(), "keypadFragment");
                        return true;

                    case R.id.nav_contacts:
                        Log.e("nav_contacts", "nav contacts!!!");
                        changeFragment(new UserContactsFragment(), "userContactsFragment");
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void changeFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment, tag)
                .commit();
    }

}
