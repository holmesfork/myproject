package com.example.holmesk.cehua.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.10 19:01
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }


}
