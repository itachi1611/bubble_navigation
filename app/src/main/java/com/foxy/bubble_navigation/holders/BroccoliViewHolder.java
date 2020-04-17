package com.foxy.bubble_navigation.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.bubble_navigation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BroccoliViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image)
    public ImageView imageView;

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.tv_description)
    public TextView tvDescription;

    @BindView(R.id.tv_price)
    public TextView tvPrice;

    public BroccoliViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
