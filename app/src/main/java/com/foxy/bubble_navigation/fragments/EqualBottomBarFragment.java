package com.foxy.bubble_navigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.foxy.bubble_navigation.R;
import com.foxy.bubble_navigation.adapters.SlidePagerAdapter;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EqualBottomBarFragment extends Fragment {

    private View inflatedView;

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager view_pager;

    @BindView(R.id.equal_navigation_bar)
    BubbleNavigationLinearView equal_navigation_bar;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_equal_bottom_bar, container, false);
        return inflatedView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<SlidePageFragment> fragList = new ArrayList<>();

        fragList.add(SlidePageFragment.newInstance(getString(R.string.shop), R.color.blue_inactive));

        fragList.add(SlidePageFragment.newInstance(getString(R.string.photos), R.color.purple_inactive));

        fragList.add(SlidePageFragment.newInstance(getString(R.string.call), R.color.green_inactive));

        SlidePagerAdapter adapter = new SlidePagerAdapter(getChildFragmentManager(), fragList);

        view_pager.setAdapter(adapter);

        view_pager.setOnTouchListener((v, event) -> true);

        equal_navigation_bar.setNavigationChangeListener((view, position) -> view_pager.setCurrentItem(position, true));

        //Change the initial active element
        int newInitialPosition = 2;

        equal_navigation_bar.setCurrentActiveItem(newInitialPosition);

        view_pager.setCurrentItem(newInitialPosition, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
