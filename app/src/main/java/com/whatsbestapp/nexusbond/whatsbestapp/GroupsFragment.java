package com.whatsbestapp.nexusbond.whatsbestapp;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private AppBarLayout appBarLayout;
    private ActionBar actionBar;

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();


        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);      // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // Remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // Remove the icon
        }




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        collapsingToolbarLayout.setVisibility(View.INVISIBLE);
        float height = getResources().getDimension(R.dimen.appbar_actionbarsize);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)appBarLayout.getLayoutParams();
        params.height = (int)height;
        appBarLayout.setLayoutParams(params);
        appBarLayout.setExpanded(false,true);

    }

    public static Fragment newInstance() {
        GroupsFragment fragment = new GroupsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }
}
