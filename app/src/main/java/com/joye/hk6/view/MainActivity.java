package com.joye.hk6.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.apkfuns.logutils.LogUtils;
import com.joye.hk6.R;
import com.joye.hk6.StatusBarHelp;
import com.joye.hk6.ac.BasePresenterAppCompatActivity;
import com.joye.hk6.adapter.MainFragmentPagerAdapter;
import com.joye.hk6.frg.ConstantsFragment;
import com.joye.hk6.internal.di.component.DaggerMainComponent;
import com.joye.hk6.internal.di.component.MainComponent;
import com.joye.hk6.internal.di.modules.MainModule;
import com.joye.hk6.vu.MainActivityVu;

import javax.inject.Inject;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/26.
 * 304622254@qq.com
 * Remeark:
 */

public class MainActivity extends BasePresenterAppCompatActivity<MainActivityVu> {
    private MainFragmentPagerAdapter mPagerAdapter;
    @Inject
    StatusBarHelp help;
    private MainComponent mainModule;
    @Override
    protected void onBindVu() {
        super.onBindVu();
        initializer();
        help.setStatusBarTintEnable(true,R.color.colorPrimary);
        setSupportActionBar(vu.toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(vu.navView);
        setupViewPager();
        vu.tabs.setupWithViewPager(vu.viewpager);

    }

    private void initializer() {
        mainModule= DaggerMainComponent.builder().mainModule(new MainModule(this)).hk6ApplicationComponent(getApplicationComponent()).build();
        mainModule.inject(this);
    }

    private void setupViewPager() {
        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(ConstantsFragment.newInstance(), "永久属性");
        mPagerAdapter.addFragment(ConstantsFragment.newInstance(), "生肖波色等");
        vu.viewpager.setAdapter(mPagerAdapter);
    }
    @Override
    protected Class<MainActivityVu> getVuClass() {
        return MainActivityVu.class;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        vu.drawerLayout.closeDrawers();
                        disposeMenuAction(menuItem);
                        return true;
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                vu.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void disposeMenuAction(MenuItem item){
//        switch (item.getItemId()){
//
//        }
        LogUtils.d(item.getItemId());
    }

}