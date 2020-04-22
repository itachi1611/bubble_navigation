package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.foxy.bubble_navigation.adapters.BroccoliAdapter;
import com.foxy.bubble_navigation.models.DataBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.samlss.broccoli.Broccoli;

public class BroccoliActivity extends AppCompatActivity {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    private Unbinder unbinder;

    private BroccoliAdapter adapter;

    private List<DataBean> mList = new ArrayList<>();

    private Map<View, Broccoli> mViewPlaceholderManager = new HashMap<>();
    private Map<View, Runnable> mTaskManager = new HashMap<>();

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

        initViews();

        initData();

        initRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < 20; i++){
            DataBean dataBean = new DataBean();
            dataBean.imageRes = sImageIds[i % sImageIds.length];
            dataBean.title = sTitles[i % sTitles.length];
            dataBean.description = sDescriptions[i % sDescriptions.length];
            dataBean.price = sPrices[i % sPrices.length];

            mList.add(dataBean);
        }
    }

    private void initRecyclerView() {
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new BroccoliAdapter(mList, mViewPlaceholderManager, mTaskManager);
        rvList.setAdapter(adapter);
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList.clear();
        for(View view : mTaskManager.keySet()) {
            view.removeCallbacks(mTaskManager.get(view));
        }

        //Prevent memory leaks when using BroccoliGradientDrawable
        for (Broccoli broccoli : mViewPlaceholderManager.values()){
            broccoli.removeAllPlaceholders();
        }

        mViewPlaceholderManager.clear();
        mTaskManager.clear();

        unbinder.unbind();
    }

}
