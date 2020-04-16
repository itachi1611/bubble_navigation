package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;

import com.foxy.bubble_navigation.adapters.SlidePagerAdapter;
import com.foxy.bubble_navigation.fragments.SlidePageFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomBarActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager viewPager;

    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bottomNavigationViewLinear;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);

        unbinder = ButterKnife.bind(this);

        List<SlidePageFragment> fragList = new ArrayList<>();
        fragList.add(SlidePageFragment.newInstance(getString(R.string.home), R.color.red_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.search), R.color.blue_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.likes), R.color.blue_grey_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.notification), R.color.green_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.profile), R.color.purple_inactive));

        SlidePagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), fragList);

        bottomNavigationViewLinear.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));
        bottomNavigationViewLinear.setBadgeValue(0, "40");
        bottomNavigationViewLinear.setBadgeValue(1, null); //invisible badge
        bottomNavigationViewLinear.setBadgeValue(2, "7");
        bottomNavigationViewLinear.setBadgeValue(3, "2");
        bottomNavigationViewLinear.setBadgeValue(4, ""); //empty badge

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationViewLinear.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationViewLinear.setNavigationChangeListener((view, position) -> {
            viewPager.setCurrentItem(position, true);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
