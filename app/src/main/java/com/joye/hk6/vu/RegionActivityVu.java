package com.joye.hk6.vu;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewStub;
import android.widget.ImageView;

import com.joye.hk6.R;
import com.joye.hk6.adapter.RegionAdapter;
import com.joye.hk6.adapter.UpdateItemRecyclerViewAdapter;
import com.joye.hk6.vu.base.ProgressSwipeRefreshRecyclerVu;

import butterknife.BindView;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/29.
 * 304622254@qq.com
 * Remeark:
 */
public class RegionActivityVu extends ProgressSwipeRefreshRecyclerVu {

    @BindView(R.id.backdrop)
    public ImageView backdrop;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    public AppBarLayout appbar;
    @BindView(R.id.main_content)
    public CoordinatorLayout mainContent;
    @BindView(R.id.lableViewStub)
    ViewStub lableViewStub;

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle("段位走势预警");
        lableViewStub.setLayoutResource(R.layout.item_region);
        lableViewStub.inflate();

    }

    @Override
    public int getResouceId() {
        return R.layout.activity_region;
    }

    @Override
    public UpdateItemRecyclerViewAdapter getRecylerViewAdapter(Context context) {
        return new RegionAdapter(context);
    }
}
