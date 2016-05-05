package com.pillreminder.pillreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onNavigationDrawerItemSelected(0);


        // start alertmanager
        Intent intent = new Intent(this, AlertPill.class);
        AlertManager.setEveryday(this, intent );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    public void onNavigationDrawerItemSelected(int position) {
        android.app.Fragment fragment=null;
        switch (position) {
            case 0: //search//todo
                fragment = getFragmentManager().findFragmentByTag(StatsFragment.TAG);

                    fragment = new StatsFragment();
                // android:label="โปรไฟล์"
                getSupportActionBar().setTitle("โปรไฟล์");
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, Listdata.TAG).commit();
                break;
            case 1: //stats
                fragment = getFragmentManager().findFragmentByTag(Listdata.TAG);

                    fragment = new Listdata_pill();
                // android:label="เพิ่มบันทึกการใช้ยา"
                getSupportActionBar().setTitle("บันทึกการพบแพทย์");
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, Listdata.TAG).commit();
                break;
            case 2: //my account //todo
                fragment = getFragmentManager().findFragmentByTag(Listdata.TAG);

                    fragment = new Listdata();
                getSupportActionBar().setTitle("บันทึกการใช้ยา");
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, Listdata.TAG).commit();
                break;
            case 3: //settings //todo

                fragment = new AlertPillReportData();
                getSupportActionBar().setTitle("สถิติการใช้ยา");
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, Listdata.TAG).commit();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int position = item.getItemId();

        android.app.Fragment fragment;
        switch (position) {
            case  R.id.nenu1: //search//todo
                onNavigationDrawerItemSelected(0);
                break;
            case  R.id.nenu2: //stats
                onNavigationDrawerItemSelected(1);
                break;
            case  R.id.nenu3: //my account //todo
                onNavigationDrawerItemSelected(2);
                break;
            case  R.id.nenu4: //graph //todo
                onNavigationDrawerItemSelected(3);
                break;

        }


         return true;

    }
//    public void displayView(int viewId) {
//
//
//
//        switch (viewId) {
//            case R.id.nav_news:
//                fragment = new NewsFragment();
//                title  = "News";
//
//                break;
//            case R.id.nav_events:
//                fragment = new EventsFragment();
//                title = "Events";
//                break;
//
//        }
//
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.commit();
//        }
//
//        // set the toolbar title
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(title);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//    }
}
