package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.foxy.bubble_navigation.fragments.EqualBottomBarFragment;

public class BottomBarEqualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar_equal);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);

        if(fragment == null) {
            fragment = new EqualBottomBarFragment();
            loadFragmentToContainer((EqualBottomBarFragment) fragment, R.id.content_frame);
        }
    }

    private void loadFragmentToContainer(EqualBottomBarFragment fragment, int resId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(resId, fragment);
        ft.commit();
    }

}
