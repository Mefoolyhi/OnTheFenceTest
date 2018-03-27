package com.example.admin.onthefencetest;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, MainFragment.newInstance());
        transaction.commit();

        navigationView.getMenu().getItem(0).setChecked(true);


    }

    NavigationView navigationView;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment selected = null;

        if (id == R.id.nav_main) {

            navigationView.getMenu().getItem(3).setChecked(false);

            navigationView.getMenu().getItem(2).setChecked(false);

            navigationView.getMenu().getItem(1).setChecked(false);
            selected = MainFragment.newInstance();

            navigationView.getMenu().getItem(0).setChecked(true);


        } else if (id == R.id.nav_favourites){

            navigationView.getMenu().getItem(3).setChecked(false);

            navigationView.getMenu().getItem(0).setChecked(false);

            navigationView.getMenu().getItem(1).setChecked(false);
            selected = FavouritesFragment.newInstance();
            navigationView.getMenu().getItem(2).setChecked(true);

        } else if (id == R.id.nav_manage) {

            navigationView.getMenu().getItem(0).setChecked(false);

            navigationView.getMenu().getItem(2).setChecked(false);

            navigationView.getMenu().getItem(1).setChecked(false);
            selected = ManageFragment.newInstance();
            navigationView.getMenu().getItem(3).setChecked(true);

        } else if (id == R.id.nav_news) {

            navigationView.getMenu().getItem(3).setChecked(false);

            navigationView.getMenu().getItem(2).setChecked(false);

            navigationView.getMenu().getItem(0).setChecked(false);
            selected = NewsFragment.newInstance();
            navigationView.getMenu().getItem(1).setChecked(true);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, selected);
        transaction.commit();

        return true;
    }
}
