package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BroccoliActivity extends AppCompatActivity {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    private Unbinder unbinder;

    private static int[] sImageIds = new int[]{
            R.mipmap.photo_1,R.mipmap.photo_2,
            R.mipmap.photo_3,R.mipmap.photo_4,
            R.mipmap.photo_5
    };

    private static int[] sPrices = new int[]{
            549, 1499, 1199, 1699, 3388
    };

    private static String[] sTitles = new String[]{
            "honor/7",
            "Huawei/MAX",
            "honor/9i",
            "Huawei/9 PLUS",
            "Huawei/P20",
    };

    private static String[] sDescriptions = new String[]{
            "2018.05",
            "2018.10",
            "2018.06",
            "2018.10",
            "2018.04",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broccoli);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
