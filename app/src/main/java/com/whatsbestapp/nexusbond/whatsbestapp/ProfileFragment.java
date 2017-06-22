package com.whatsbestapp.nexusbond.whatsbestapp;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private VpAdapter adapter;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private AppBarLayout appBarLayout;
    private  ActionBar actionBar;


    private static final String mLive = "Live";
    private static final String mFinished = "Finished";
    private static final String mInteractions = "Interactions";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mtabLayout = (TabLayout) view.findViewById(R.id.profile_layout_tab);

        mviewPager = (ViewPager) view.findViewById(R.id.profile_view_viewpager);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();


        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);      // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // Remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // Remove the icon
            actionBar.setHomeAsUpIndicator(R.drawable.fab_plus_icon);
        }


        float height = getResources().getDimension(R.dimen.appbar_height);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)appBarLayout.getLayoutParams();
        params.height = (int)height;
        appBarLayout.setLayoutParams(params);
        appBarLayout.setExpanded(true);


        collapsingToolbarLayout.setVisibility(View.VISIBLE);
        collapsingToolbarLayout.setTitleEnabled(false);
        fragmentData();
        functions();



//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        collapsingToolbarLayout.setVisibility(View.VISIBLE);
        collapsingToolbarLayout.setTitleEnabled(false);
        appBarLayout.setExpanded(true,true);



    }


    public static Fragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    private void fragmentData(){

        Viewpagersetup(mviewPager);

        mtabLayout.setupWithViewPager(mviewPager);
    }

    private void Viewpagersetup(ViewPager viewPager)
    {
        adapter = new VpAdapter(getChildFragmentManager());
        adapter.addFragment(new LiveFragment(),mLive);
        adapter.addFragment(new FinishedFragment(),mFinished);
        adapter.addFragment(new InteractionsFragment(),mInteractions);
        viewPager.setAdapter(adapter);


    }

    private void functions() {
        // set listener to do something then item selected
        mviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtabLayout));
        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


    }

    public class VpAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {


            return mFragmentTitleList.get(position);
        }
    }
}
