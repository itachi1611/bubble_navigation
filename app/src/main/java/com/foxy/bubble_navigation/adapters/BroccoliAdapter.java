package com.foxy.bubble_navigation.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.bubble_navigation.R;
import com.foxy.bubble_navigation.holders.BroccoliViewHolder;
import com.foxy.bubble_navigation.models.DataBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.samlss.broccoli.Broccoli;
import me.samlss.broccoli.BroccoliGradientDrawable;
import me.samlss.broccoli.PlaceholderParameter;

public class BroccoliAdapter extends RecyclerView.Adapter<BroccoliViewHolder> {

    private List<DataBean> mList = new ArrayList<>();

    private Map<View, Broccoli> mViewPlaceholderManager = new HashMap<>();
    private Map<View, Runnable> mTaskManager = new HashMap<>();

    @NonNull
    @Override
    public BroccoliViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BroccoliViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_broccoli, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BroccoliViewHolder holder, int position) {
        Broccoli broccoli = mViewPlaceholderManager.get(holder.itemView);
        if(broccoli == null) {
            broccoli = new Broccoli();
            mViewPlaceholderManager.put(holder.itemView, broccoli);
        }

        broccoli.removeAllPlaceholders();

        broccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(holder.tvTitle)
                .setDrawable(new BroccoliGradientDrawable(Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0, 1000, new LinearInterpolator()))
                .build());
        broccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(holder.imageView)
                .setDrawable(new BroccoliGradientDrawable(Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0, 1000, new LinearInterpolator()))
                .build());
        broccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(holder.tvPrice)
                .setDrawable(new BroccoliGradientDrawable(Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0, 1000, new LinearInterpolator()))
                .build());
        broccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(holder.tvDescription)
                .setDrawable(new BroccoliGradientDrawable(Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0, 1000, new LinearInterpolator()))
                .build());

        broccoli.show();

        //delay to show the data
        Runnable task = mTaskManager.get(holder.itemView);
        if (task == null){
            final Broccoli finalBroccoli = broccoli;
            task = new Runnable() {
                @Override
                public void run() {
                    //when you need to update data, you must to call the remove or the clear method.
                    finalBroccoli.removeAllPlaceholders();

                    if (mList.isEmpty()){
                        return;
                    }

                    holder.imageView.setImageResource(mList.get(i).imageRes);
                    holder.tvPrice.setText("¥ "+String.valueOf(mList.get(i).price));
                    holder.tvTitle.setText(mList.get(i).title);
                    holder.tvDescription.setText(mList.get(i).description);
                }
            };
            mTaskManager.put(holder.itemView, task);
        }else{
            holder.itemView.removeCallbacks(task);
        }
        holder.itemView.postDelayed(task, 3000);

    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }
}
