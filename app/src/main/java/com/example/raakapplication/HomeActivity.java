package com.example.raakapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.menuTabs);
        viewPager = findViewById(R.id.viewMenus);

        tabLayout.setupWithViewPager(viewPager);
        menuAdapter ma = new menuAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ma.addFragment(new BreakfastFragment(), "Breakfast");
        ma.addFragment(new LunchFragment(), "Lunch");
        ma.addFragment(new DinnerFragment(), "Dinner");
        viewPager.setAdapter(ma);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_constraint, new Fragment()).commit();
        nav();
    }

    public void nav(){
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.nav_menu);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_menu:
                    return true;
                case R.id.nav_booking:
                    startActivity(new Intent(getApplicationContext(),
                            BookingActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_events:
                    startActivity(new Intent(getApplicationContext(),
                            EventsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}