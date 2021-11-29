package com.example.raakapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ArrayList<MenuItem> breakfast;
    public ArrayList<MenuItem> lunch;
    public ArrayList<MenuItem> dinner;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.menuTabs);
        viewPager = findViewById(R.id.viewMenus);


        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();

        getMenuItems();
        nav();

    }

    public void setFragments(){
        tabLayout.setupWithViewPager(viewPager);
        menuAdapter ma = new menuAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ma.addFragment(new BreakfastFragment(), "Breakfast");
        ma.addFragment(new LunchFragment(), "Lunch");
        ma.addFragment(new DinnerFragment(), "Dinner");
        viewPager.setAdapter(ma);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_constraint, new Fragment()).commit();
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

    public void getMenuItems(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        breakfast.clear();
//        lunch.clear();
//        dinner.clear();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Menu...");
        progressDialog.show();

        databaseReference.child("MenuItems").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for(DataSnapshot data : task.getResult().getChildren()){
                    if(data.getKey().equals("Breakfast")) {
                        for (DataSnapshot innerData : data.getChildren()){
                            MenuItem menuItem = new MenuItem(innerData.child("Catagory").getValue().toString(), innerData.child("Description").getValue().toString(),
                                    innerData.child("ImagePath").getValue().toString(), innerData.child("Name").getValue().toString(),
                                    innerData.child("Price").getValue().toString());
                            breakfast.add(menuItem);
                        }
                    }
                    if(data.getKey().equals("Lunch")){
                        for (DataSnapshot innerData : data.getChildren()){
                            MenuItem menuItem = new MenuItem(   innerData.child("Catagory").getValue().toString(), innerData.child("Description").getValue().toString(),
                                    innerData.child("ImagePath").getValue().toString(), innerData.child("Name").getValue().toString(),
                                    innerData.child("Price").getValue().toString());
                            lunch.add(menuItem);
                        }
                    }
                    if(data.getKey().equals("Dinner")){
                        for (DataSnapshot innerData : data.getChildren()){
                            MenuItem menuItem = new MenuItem(   innerData.child("Catagory").getValue().toString(), innerData.child("Description").getValue().toString(),
                                    innerData.child("ImagePath").getValue().toString(), innerData.child("Name").getValue().toString(),
                                    innerData.child("Price").getValue().toString());
                            dinner.add(menuItem);
                        }
                    }
                }
                progressDialog.dismiss();
                setFragments();
            }
        });

    }

}