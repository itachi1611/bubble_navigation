package com.foxy.bubble_navigation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity  {

    /**
     * Bubble Navigation
     * MinSDK = 16
     * dependencies {
     *     implementation 'com.gauravk.bubblenavigation:bubblenavigation:1.0.7'
     * }
     *
     * Use com.gauravk.bubblenavigation.BubbleNavigationConstraintView with bnc_mode
     * * spread : equally distributes the child elements
     * * packed : elements are packed with center gravity
     * * inside : inside elements are equally distributed
     *
     * or use com.gauravk.bubblenavigation.BubbleNavigationLinearView with no bnc_mode
     *
     * Attributes
     * bt_active	Sets to Active State
     * bt_colorActive	When in Active State, uses this color for the Icon and Title
     * bt_colorInctive	When in Inactive State, uses this color for the Icon and Title
     * bt_icon	Sets the Icon Drawable
     * bt_iconWidth	Updates the Icon Width
     * bt_iconHeigth	Updates the Icon Height
     * bt_title	Sets the Title Text
     * bt_titleSize	Updates the Tilte Text Size in sp
     * bt_shape	Sets the Background Drawable. Use TransitionDrawable to get fade in-out effect when toggling
     * bt_showShapeAlways	If true and using Normal drawable, background shape remains visible always
     * bt_shapeColor	Changes the tint color of the shape. N/A when using TransitionDrawable or showShapeAlways is true.
     * bt_duration	Sets time duration for toggle animation to complete in ms
     * bt_padding	Sets the internal padding in dp
     * bt_titlePadding	Sets the title padding in dp
     * bt_badgeText	Sets the text for the badge
     * bt_badgeTextSize	Sets the font size of the badge text
     * bt_badgeTextColor	Sets the text color of the badge
     * bt_badgeBackgroundColor	Sets the background color of the badge
     *
     */

    /**
     * Liquid Swipe
     * MinSDK = 21
     * allprojects {
     * 	repositories {
     * 		...
     * 		maven { url 'https://jitpack.io' }
     *        }
     * }
     * implementation 'com.github.Chrisvin:LiquidSwipe:1.3'
     *
     * Use LiquidSwipeViewPager instead of the normal ViewPager
     *
     * Use a LiquidSwipeLayout as the base container in the fragment layouts
     * It also support LiquidSwipeFrameLayout and LiquidSwipeLinearLayout
     */

    /**
     * Broccoli
     * dependencies {
     *     implementation 'me.samlss:broccoli:1.0.0'
     * }
     */

    /**
     * Progress button
     * implementation 'com.github.razir.progressbutton:progressbutton:2.1.0'
     */

    /**
     * dependencies {
     *     // Material Dialog Library
     *     implementation 'com.shreyaspatil:MaterialDialog:2.1'
     *
     *     // Material Design Library
     *     implementation 'com.google.android.material:material:1.0.0'
     *
     *     // Lottie Animation Library
     *     implementation 'com.airbnb.android:lottie:3.3.6'
     * }
     *
     * Dialog state listerner
     *  ...
     *        mDialog.setOnShowListener(this);
     *        mDialog.setOnCancelListener(this);
     *        mDialog.setOnDismissListener(this);
     *     }
     *
     *     @Override
     *     public void onShow(DialogInterface dialogInterface) {
     *         // Dialog is Displayed
     *     }
     *
     *     @Override
     *     public void onCancel(DialogInterface dialogInterface) {
     *         // Dialog is Cancelled
     *     }
     *
     *     @Override
     *     public void onDismiss(DialogInterface dialogInterface) {
     *         // Dialog is Dismissed
     *     }
     * }
     */

    /**
     * Android view Animations
     * dependencies {
     *         compile 'com.android.support:support-compat:25.1.1'
     *         compile 'com.daimajia.easing:library:2.0@aar'
     *         compile 'com.daimajia.androidanimations:library:2.3@aar'
     * }
     */

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btnOpenTop)
    protected void onOpenTop() {
        startActivity(new Intent(this, TopBarActivity.class));
    }

    @OnClick(R.id.btnOpenTopFloat)
    protected void onOpenTopFloat() {
        startActivity(new Intent(this, TopBarFloatActivity.class));
    }

    @OnClick(R.id.btnOpenBottomEqual)
    protected void onOpenBottomEqual() {
        startActivity(new Intent(this, BottomBarEqualActivity.class));
    }

    @OnClick(R.id.btnOpenBottom)
    protected void onOpenBottom() {
        startActivity(new Intent(this, BottomBarActivity.class));
    }

    @OnClick(R.id.tvBroccoli)
    protected void onBroccoli() {
        startActivity(new Intent(this, BroccoliActivity.class));
    }

    @OnClick(R.id.tvProgressButton)
    protected void onProgressButton() { startActivity(new Intent(this, ProgressButtonActivity.class));}

    @OnClick(R.id.tvMaterialDialog)
    protected void onMaterialDialog() {
        startActivity(new Intent(this, MaterialDialogActivity.class));
    }

    @OnClick(R.id.tvAnimationView)
    protected void onAnimationView() {
        startActivity(new Intent(this, AnimationViewActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
