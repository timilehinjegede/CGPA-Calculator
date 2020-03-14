package com.example.playhard;

import android.os.Bundle;

import com.example.playhard.ForCalculate.CalculateFragment;
import com.example.playhard.ForCalculate.SectionsPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.calculate: {
                getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new CalculateFragment()).commit();
                break;
            }
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new SettingsFragment()).commit();
                break;
        }
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new CalculateFragment()).commit();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
