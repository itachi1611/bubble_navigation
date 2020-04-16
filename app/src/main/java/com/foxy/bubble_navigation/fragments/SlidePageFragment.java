package com.foxy.bubble_navigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.foxy.bubble_navigation.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SlidePageFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_BG_COLOR = "arg_bg_color";

    private String title = "Default title";
    private int bgColorResId = R.color.blue_grey_inactive;

    private View inflatedView;

    @BindView(R.id.screen_slide_title)
    TextView screen_slide_title;

    private Unbinder unbinder;

    public SlidePageFragment(){}

    public static SlidePageFragment newInstance(String title, int bgColorId) {
        SlidePageFragment fragment = new SlidePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_BG_COLOR, bgColorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            bgColorResId = getArguments().getInt(ARG_BG_COLOR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        return inflatedView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inflatedView.setBackgroundColor(ContextCompat.getColor(getContext(), bgColorResId));
        screen_slide_title.setText(title.trim());
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
