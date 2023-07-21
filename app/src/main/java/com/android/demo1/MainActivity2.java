package com.android.demo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.demo1.Fragments.Cart.CartFragment;
import com.android.demo1.Fragments.Home.HomeFragment;
import com.android.demo1.Fragments.User.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {
    BottomNavigationView navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        viewRef();

        Intent intent = getIntent();
        Account account = (Account) intent.getParcelableExtra("account");

        Toast.makeText(MainActivity2.this, "value: "+ account, Toast.LENGTH_SHORT).show();

        setFragment(new HomeFragment());

        navMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.cart:
                        setFragment(new CartFragment());
                        break;
                    case R.id.user:
                        setFragment(new UserFragment());
                        break;
                }


                return true;
            }
        });
    }

    private void viewRef() {
        navMenu = findViewById(R.id.navMenu);
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }
}