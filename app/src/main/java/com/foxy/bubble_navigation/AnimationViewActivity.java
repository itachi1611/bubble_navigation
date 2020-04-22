package com.foxy.bubble_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AnimationViewActivity extends AppCompatActivity {

    @BindView(R.id.rvList)
    ListView rvList;

    @BindView(R.id.tvTitle)
    View tvTitle;

    private AnimationEffectAdapter adapter;

    private YoYo.YoYoString rope;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_view);

        initViews();

        adapter = new AnimationEffectAdapter(this);
        rvList.setAdapter(adapter);

        rvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(rope != null) {
                    rope.stop(true);
                }

                Techniques techniques = (Techniques) view.getTag();
                rope = YoYo.with(techniques)
                        .duration(1200)
                        .repeat(YoYo.INFINITE)
                        .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                Toast.makeText(AnimationViewActivity.this, "canceled previous animation", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(tvTitle);
            }
        });

        tvTitle.setOnClickListener(v -> {
            if(rope != null) {
                rope.stop(true);
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            // after start,just click mTarget view, rope is not init
            rope = YoYo.with(Techniques.FadeIn).duration(2000).playOn(tvTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, TestActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    static class AnimationEffectAdapter extends BaseAdapter {

        private Context mContext;

        public AnimationEffectAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return Techniques.values().length;
        }

        @Override
        public Object getItem(int position) {
            return Techniques.values()[position].getAnimator();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_animation,null,false);
            TextView t = view.findViewById(R.id.tvItem);
            Object o = getItem(position);
            int start = o.getClass().getName().lastIndexOf(".") + 1;
            String name = o.getClass().getName().substring(start);
            t.setText(name);
            view.setTag(Techniques.values()[position]);
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
