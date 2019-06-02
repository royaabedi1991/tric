package com.a3rick.a3rick.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.a3rick.a3rick.fragments.CategoryFragment;
import com.a3rick.a3rick.fragments.MyVideoFragment;
import com.a3rick.a3rick.fragments.VitrinFragment;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;



    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragments = new Fragment[]{

                new MyVideoFragment(),
                new CategoryFragment(),
                new VitrinFragment(),
        };

    }

    @Override
    public Fragment getItem(int position) {


        return fragments[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
