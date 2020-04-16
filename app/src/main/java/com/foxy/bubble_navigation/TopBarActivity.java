package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.foxy.bubble_navigation.adapters.SlidePagerAdapter;
import com.foxy.bubble_navigation.fragments.SlidePageFragment;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.jem.liquidswipe.LiquidSwipeViewPager;
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopBarActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager view_pager;

    @BindView(R.id.top_navigation_constraint)
    BubbleNavigationConstraintView top_navigation_constraint;

    private List<SlidePageFragment> fragList;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);

        unbinder = ButterKnife.bind(this);

        fragList = new ArrayList<>();

        fragList.add(SlidePageFragment.newInstance(getString(R.string.restaurant), R.color.orange_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.room), R.color.red_inactive));
        fragList.add(SlidePageFragment.newInstance(getString(R.string.happy), R.color.green_inactive));

        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager(), fragList);

        view_pager.setAdapter(adapter);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                top_navigation_constraint.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        top_navigation_constraint.setNavigationChangeListener((view, position) -> {
            view_pager.setCurrentItem(position, true);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
