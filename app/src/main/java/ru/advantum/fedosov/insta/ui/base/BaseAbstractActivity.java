package ru.advantum.fedosov.insta.ui.base;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.advantum.fedosov.insta.R;
import ru.advantum.fedosov.insta.annotation.ActivityRef;
import ru.advantum.fedosov.insta.event.BaseEvent;

// TODO add navigation support

/**
 * Base activity implementation. Use this class for auto apply basic functionality
 * <p>
 * Use annotation {@link ActivityRef} for configuring activity workaround.
 * Class is not final and can be modified in project.
 * <pre><code>
 *     {@literal @}ActivityRef(resource = R.layout.activity_main, knifeEnabled = true,
 *     busEnabled = true, isHomeAsUp = true)
 *     public class MainActivity extends BaseAbstractActivity {
 *         ...
 *     }
 * </code></pre>
 * </p>
 * <p>
 * Usually several functions are implemented here:
 * <ul>
 * <li>{@link #showProgressDialog(boolean)} &ndash; Shows progress dialog without message</li>
 * <li>{@link #hideKeyboard()} &ndash; Hides keyboard if it was shown</li>
 * </ul>
 * </p>
 *
 * @see ActivityRef
 */
@ActivityRef
public abstract class BaseAbstractActivity extends AppCompatActivity {

    /**
     * Common extra key for activities
     */
    protected static final String EXTRA_ID = "EXTRA_ID";

    private ProgressDialog mProgressDialog;

    private ActivityRef activityRef = getClass().getAnnotation(ActivityRef.class);
    protected Toolbar mToolbar;
    @Nullable
    @Bind(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    protected NavigationFragment mNavigationFragment;
    protected ActionBarDrawerToggle mDrawerToggle;
    /**
     * Calls dialog with progress bar for blocking UI, while some background task have to be executed
     */
    protected void showProgressDialog(boolean show) {
        showProgressDialog(show, "Пожалуйста, подождите");
    }

    /**
     * Calls dialog with progress bar for blocking UI, while some background task have to be executed
     */
    protected void showProgressDialog(boolean show, String message) {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        if (!show) return;
        hideKeyboard();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * In OnCreate we have workaround with 3 annotation parameters:
     * <ul>
     * <li>resource &ndash; for setContentView method</li>
     * <li>toolbarResId &ndash; for toolbar activation (if it was defined)</li>
     * <li>knifeEnabled &ndash; for injecting views, if it is set to True</li>
     * </ul>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityRef.resource() != 0) {
            if (activityRef.navigation()) {
                setContentView(R.layout.activity_navigation);
                initActivityView(activityRef.resource());
            } else setContentView(activityRef.resource());

            if (activityRef.toolbarResId() != 0) {
                mToolbar = (Toolbar) findViewById(activityRef.toolbarResId());
                setSupportActionBar(mToolbar);
            }
            if (activityRef.knifeEnabled()) ButterKnife.bind(this);
            if (activityRef.navigation()) initNavigationFragment();
        }
    }

    private void initActivityView(int res) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_frame);
        if (frameLayout != null) {
            frameLayout.addView(getLayoutInflater().inflate(res, frameLayout, false));
        }
    }

    private void initNavigationFragment() {
        mNavigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    public  void closeDrawerLayout(){
        mDrawerLayout.closeDrawers();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    /**
     * Here we register class in eventBus if needed
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (activityRef.busEnabled() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * Here we unregister class in eventBus if needed
     */
    @Override
    protected void onStop() {
        if (activityRef.busEnabled() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    /**
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        restoreActionBar();
        return result;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } super.onBackPressed();
    }

    protected void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayShowHomeEnabled(activityRef.navigation() || activityRef.isHomeAsUp());
        if (activityRef.isHomeAsUp()) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected void hideKeyboard() {
        View v = getCurrentFocus();
        if (v != null) {
            ((InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBaseEvent(BaseEvent unused) {
        // do nothing. for EventBus, if it would be registered
    }
}
