package com.javierpinya.p1_inspcam.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.javierpinya.p1_inspcam.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MenuActivity extends AppCompatActivity{

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment f = null;

            switch (menuItem.getItemId()) {
                case R.id.navigation_dashboard:
                    f = new VehiculoFragment();
                    return true;
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }

            if (f != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedorvehiculo, f)
                        .commit();
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, new VehiculoFragment())
                .commit();


    }

}
