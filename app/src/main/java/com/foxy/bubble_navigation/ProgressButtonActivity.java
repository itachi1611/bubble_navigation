package com.foxy.bubble_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.razir.progressbutton.ButtonTextAnimatorExtensionsKt;
import com.github.razir.progressbutton.DrawableButton;
import com.github.razir.progressbutton.DrawableButtonExtensionsKt;
import com.github.razir.progressbutton.DrawableParams;
import com.github.razir.progressbutton.ProgressButtonHolderKt;
import com.github.razir.progressbutton.ProgressParams;
import com.github.razir.progressbutton.TextChangeAnimatorParams;
import com.google.android.material.button.MaterialButton;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ProgressButtonActivity extends AppCompatActivity {

    @BindView(R.id.buttonAnimatedDrawable)
    MaterialButton buttonAnimatedDrawable;

    @BindView(R.id.buttonProgressMixed)
    MaterialButton buttonProgressMixed;

    @BindView(R.id.buttonProgressRightText)
    Button buttonProgressRightText;

    @BindView(R.id.buttonProgressLeftText)
    Button buttonProgressLeftText;

    @BindView(R.id.buttonProgressCenter)
    Button buttonProgressCenter;

    @BindView(R.id.buttonProgressCustomStyle)
    Button buttonProgressCustomStyle;

    @BindView(R.id.rvList)
    RecyclerView rvList;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);

        initViews();

        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonAnimatedDrawable);
        ProgressButtonHolderKt.bindProgressButton(this, buttonProgressMixed);

        //
        ProgressButtonHolderKt.bindProgressButton(this, buttonProgressRightText);
        ProgressButtonHolderKt.bindProgressButton(this, buttonProgressLeftText);
        ProgressButtonHolderKt.bindProgressButton(this, buttonProgressCenter);
        ProgressButtonHolderKt.bindProgressButton(this, buttonProgressCustomStyle);

        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonProgressRightText);
        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonProgressLeftText);
        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonProgressCustomStyle);

        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonProgressCenter, textChangeAnimatorParams -> {
            textChangeAnimatorParams.setFadeInMills(300);
            textChangeAnimatorParams.setFadeOutMills(300);
            return Unit.INSTANCE;
        });

        buttonProgressMixed.setOnClickListener(v -> {
            showMixed(buttonProgressMixed);
        });

        buttonAnimatedDrawable.setOnClickListener(v -> {
            showAnimatedDrawable(buttonAnimatedDrawable);
        });

        buttonProgressRightText.setOnClickListener(v -> {
            showProgressRight(buttonProgressRightText);
        });

        buttonProgressLeftText.setOnClickListener(v -> {
            showProgressLeft(buttonProgressLeftText);
        });

        buttonProgressCenter.setOnClickListener(v -> {
            showProgressCenter(buttonProgressCenter);
        });

        buttonProgressCustomStyle.setOnClickListener(v -> {
            showProgressCustom(buttonProgressCustomStyle);
        });

        new Handler().postDelayed(() -> {
            buttonProgressMixed.setVisibility(View.GONE);
            buttonAnimatedDrawable.setVisibility(View.GONE);
            buttonProgressRightText.setVisibility(View.GONE);
            buttonProgressLeftText.setVisibility(View.GONE);
            buttonProgressCenter.setVisibility(View.GONE);
            buttonProgressCustomStyle.setVisibility(View.GONE);

            rvList.setLayoutManager(new LinearLayoutManager(this));
            rvList.setAdapter(new ButtonsAdapter(this));
        }, 10000);

    }

    static class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.Holder>{
        private final LifecycleOwner lifecycleOwner;
        private final Set<Integer> inProgress = new HashSet<>();

        public ButtonsAdapter(LifecycleOwner lifecycleOwner) {
            this.lifecycleOwner = lifecycleOwner;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class Holder extends RecyclerView.ViewHolder {

            private MaterialButton buttonProgress;
            private TextView number;

            public Holder(@NonNull View itemView) {
                super(itemView);
                buttonProgress = itemView.findViewById(R.id.buttonProgress);
                number = itemView.findViewById(R.id.number);
                ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(buttonProgress);
                ProgressButtonHolderKt.bindProgressButton(lifecycleOwner, buttonProgress);
                buttonProgress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inProgress.add(getAdapterPosition());
                        DrawableButtonExtensionsKt.showProgress(buttonProgress, new Function1<ProgressParams, Unit>() {
                            @Override
                            public Unit invoke(ProgressParams progressParams) {
                                progressParams.setProgressColor(Color.WHITE);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                });
            }

            public void bind(int position) {
                number.setText("position #" + position);
                ProgressButtonHolderKt.cleanUpDrawable(buttonProgress);
                if (!inProgress.contains(position)) {
                    buttonProgress.setText(R.string.submit);
                } else {
                    DrawableButtonExtensionsKt.showProgress(buttonProgress, new Function1<ProgressParams, Unit>() {
                        @Override
                        public Unit invoke(ProgressParams progressParams) {
                            progressParams.setProgressColor(Color.WHITE);
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
        }
    }

    private void showMixed(final Button button) {
        final Drawable animatedDrawable = ContextCompat.getDrawable(this, R.drawable.animated_check);

        //Defined bounds are required for your drawable
        int drawableSize = getResources().getDimensionPixelSize(R.dimen.doneSize);
        animatedDrawable.setBounds(0, 0, drawableSize, drawableSize);

        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            return Unit.INSTANCE;
        });

        button.setEnabled(false);

        new Handler().postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.showDrawable(button, animatedDrawable, drawableParams -> {
                drawableParams.setButtonTextRes(R.string.saved);
                return Unit.INSTANCE;
            });

            new Handler().postDelayed(() -> DrawableButtonExtensionsKt.hideDrawable(button, R.string.mixedBehaviour), 2000);
        }, 3000);
    }

    private void showAnimatedDrawable(final Button button) {
        Drawable animatedDrawable = ContextCompat.getDrawable(this, R.drawable.animated_check);

        //Defined bounds are required for your drawable
        int drawableSize = getResources().getDimensionPixelSize(R.dimen.doneSize);
        animatedDrawable.setBounds(0, 0, drawableSize, drawableSize);
        button.setEnabled(false);

        DrawableButtonExtensionsKt.showDrawable(button, animatedDrawable, drawableParams -> {
            drawableParams.setButtonTextRes(R.string.saved);
            drawableParams.setTextMarginRes(R.dimen.drawableTextMargin);
            return Unit.INSTANCE;
        });

        new Handler().postDelayed(() -> {
            DrawableButtonExtensionsKt.hideDrawable(button, R.string.animatedDrawable);
            button.setEnabled(true);
        }, 3000);
    }

    private void showProgressRight(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler().postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progressRight);
        }, 3000);
    }

    private void showProgressLeft(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_TEXT_START);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler().postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progressLeft);
        }, 3000);
    }

    private void showProgressCenter(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_CENTER);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler().postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progressCenter);
        }, 3000);
    }

    private void showProgressCustom(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, new Function1<ProgressParams, Unit>() {
            @Override
            public Unit invoke(ProgressParams progressParams) {
                progressParams.setButtonTextRes(R.string.loading);
                progressParams.setProgressColors(new int[] {Color.WHITE, Color.MAGENTA, Color.GREEN});
                progressParams.setGravity(DrawableButton.GRAVITY_TEXT_END);
                progressParams.setProgressRadiusRes(R.dimen.progressRadius);
                progressParams.setProgressStrokeRes(R.dimen.progressStroke);
                progressParams.setTextMarginRes(R.dimen.textMarginStyled);
                return Unit.INSTANCE;
            }
        });
        button.setEnabled(false);

        new Handler().postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progressCustomStyle);
        }, 5000);
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
