package com.foxy.bubble_navigation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bitvale.switcher.SwitcherC;
import com.bitvale.switcher.SwitcherX;

import butterknife.BindView;
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
     * Switcher
     * MinSDK = 19
     * dependencies {
     *   implementation 'com.bitvale:switcher:1.0.9'
     * }
     * Usage:
     * <com.bitvale.switcher.SwitcherX // or SwitcherC
     *     android:id="@+id/switcher"
     *     android:layout_width="wrap_content"
     *     android:layout_height="wrap_content"
     *     app:switcher_on_color="@color/on_color"
     *     app:switcher_off_color="@color/off_color"
     *     app:switcher_icon_color="@color/icon_color" />
     * Properties:
     * android:checked (boolean) -> default true
     * app:switcher_on_color (color) -> default #48ea8b
     * app:switcher_off_color (color) -> default #ff4651
     * app:switcher_icon_color (color) -> default white
     * app:elevation (dimension) -> default 4dp
     */

    @BindView(R.id.switcher_x)
    SwitcherX switcher_x;

    @BindView(R.id.switcher_c)
    SwitcherC switcher_c;

    private Unbinder unbinder;

    private CompoundButton.OnCheckedChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setOnCheckedChangeListener();
        switcher_x.setOnCheckedChangeListener();
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.listener = listener;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
