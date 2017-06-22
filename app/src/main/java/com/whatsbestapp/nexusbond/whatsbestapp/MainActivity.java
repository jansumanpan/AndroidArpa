package com.whatsbestapp.nexusbond.whatsbestapp;




import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    android.content.res.Resources res;

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationViewEx mBottomNavigationViewEx;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private AppBarLayout appBarLayout;
    private ActionBar actionBar;
    private LinearLayout.LayoutParams params;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
//        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.main_menu_FloatingAction);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.main_view_bottomnav);

        res = getResources();
        height = res.getDimension(R.dimen.appbar_actionbarsize);

       setSupportActionBar(mToolbar);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);      // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // Remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // Remove the icon
        }

        params = (LinearLayout.LayoutParams)appBarLayout.getLayoutParams();
        params.height = (int)height;


        attributes();
        bottom_nav();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        HomeFragment navTopicFragment = new HomeFragment();
        transaction.replace(R.id.main_layout_frame, navTopicFragment);
        transaction.commit();

    }


    private void bottom_nav(){



        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.bottomnav_menu_home:
                        selectedFragment = HomeFragment.newInstance();

                        break;
                    case R.id.bottomnav_menu_search:
                        selectedFragment = SearchFragment.newInstance();

                        break;
                    case R.id.bottomnav_menu_groups:
                        selectedFragment = GroupsFragment.newInstance();

                        break;
                    case R.id.bottomnav_menu_watch:
                        selectedFragment = WatchFragment.newInstance();
                        break;
                    case R.id.bottomnav_menu_profile:
                        selectedFragment = ProfileFragment.newInstance();

                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_layout_frame, selectedFragment);
                transaction.commit();
                return true;
            }
        });



    }

    private void attributes(){
        mBottomNavigationViewEx.setTextVisibility(false);
        mBottomNavigationViewEx.enableItemShiftingMode(false);
        mBottomNavigationViewEx.enableShiftingMode(false);

    }

    private void fab(){

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
