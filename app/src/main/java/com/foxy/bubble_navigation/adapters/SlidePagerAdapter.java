package com.foxy.bubble_navigation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.foxy.bubble_navigation.fragments.SlidePageFragment;

import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<SlidePageFragment> mList;
    private FragmentManager fragmentManager;

    public SlidePagerAdapter(@NonNull FragmentManager fm, List<SlidePageFragment> mList) {
        super(fm);
        this.mList = mList;
        this.fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position >= 0 && position < mList.size()) {
            return mList.get(position);
        }
        return new SlidePageFragment();
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
