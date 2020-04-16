package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;

import com.foxy.bubble_navigation.adapters.SlidePagerAdapter;
import com.foxy.bubble_navigation.fragments.SlidePageFragment;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopBarFloatActivity extends AppCompatActivity {

    @BindView(R.id.floating_top_bar_navigation)
    BubbleNavigationConstraintView floatingTopBarNavigation;

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager viewPager;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar_float);

        unbinder = ButterKnife.bind(this);

        floatingTopBarNavigation.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));

        floatingTopBarNavigation.setBadgeValue(0, "3");
        floatingTopBarNavigation.setBadgeValue(1, "9+");

        //invisible badge
        List<SlidePageFragment> fragList = new ArrayList<>();

        fragList.add(SlidePageFragment.newInstance(getString(R.string.home), R.color.red_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.search), R.color.blue_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.likes), R.color.blue_grey_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.notification), R.color.green_inactive));

        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager(), fragList);

        viewPager.setAdapter(adapter);

        //disable swipe
        viewPager.setOnTouchListener((v, event) -> true);

        floatingTopBarNavigation.setNavigationChangeListener((view, position) -> {
            viewPager.setCurrentItem(position, true);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
