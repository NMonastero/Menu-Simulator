package com.example.menusimulator;

import android.os.Bundle;

import com.example.menusimulator.ui.Information;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    //still needs image variables
    Information green = new Information("Green",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0);

    Information red = new Information("Red",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0);

    Information blue = new Information("Blue",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Information goonDex[] = new Information[3];
        goonDex[0] = green;
        goonDex[1] = red;
        goonDex[2] = blue;

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.info, R.id.stats, R.id.moves)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//        final fragment info =findViewById(R.id.info);
    }

}
