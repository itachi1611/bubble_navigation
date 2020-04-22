package com.foxy.bubble_navigation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.shreyaspatil.MaterialDialog.interfaces.OnCancelListener;
import com.shreyaspatil.MaterialDialog.interfaces.OnDismissListener;
import com.shreyaspatil.MaterialDialog.interfaces.OnShowListener;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MaterialDialogActivity extends AppCompatActivity implements OnShowListener, OnCancelListener, OnDismissListener {

    private MaterialDialog mSimpleDialog;
    private MaterialDialog mAnimatedDialog;
    private BottomSheetMaterialDialog mSimpleBottomSheetDialog;
    private BottomSheetMaterialDialog mAnimatedBottomSheetDialog;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialog);

        initViews();
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    private void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_simple_dialog)
    protected void initSimpleDialog() {
        mSimpleDialog = new MaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this file ?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete, (dialogInterface, which) -> {
                    showToast("Deleted !");
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", R.drawable.ic_close, (dialogInterface, which) -> {
                    showToast("Cancelled !");
                    dialogInterface.dismiss();
                }).build();
        mSimpleDialog.show();
    }

    @OnClick(R.id.button_animated_dialog)
    protected void initAnimatedDialog() {
        mAnimatedDialog = new MaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this file?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete, (dialogInterface, i) -> {
                    showToast("Deleted !");
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", R.drawable.ic_close, (dialogInterface, which) -> {
                    showToast("Cancelled !");
                    dialogInterface.dismiss();
                })
                .setAnimation(R.raw.delete_anim)
                .build();
        mAnimatedDialog.show();
    }

    @OnClick(R.id.button_simple_bottomsheet_dialog)
    protected void initSimpleBottomsheetDialog() {
        mSimpleBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this file?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete, (dialogInterface, i) -> {
                    showToast("Deleted !");
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", R.drawable.ic_close, (dialogInterface, which) -> {
                    showToast("Cancelled !");
                    dialogInterface.dismiss();
                })
                .build();
        mSimpleBottomSheetDialog.show();
    }

    @OnClick(R.id.button_animated_bottomsheet_dialog)
    protected void initAnimatedBottomsheetDialog() {
        mAnimatedBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this file?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete, (dialogInterface, i) -> {
                    showToast("Deleted !");
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", R.drawable.ic_close, (dialogInterface, which) -> {
                    showToast("Cancelled !");
                    dialogInterface.dismiss();
                })
                .setAnimation(R.raw.delete_anim)
                .build();
        mAnimatedBottomSheetDialog.show();
        // Get Animation View
        LottieAnimationView animationView = mAnimatedBottomSheetDialog.getAnimationView();
        // Do operations on animationView
        Log.d("NamNT", String.valueOf(animationView));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

    }

}
