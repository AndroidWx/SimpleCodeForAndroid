package com.joye.hk6.view;

import android.support.v7.widget.LinearLayoutManager;

import com.joye.basepresentation.internal.di.HasComponent;
import com.joye.hk6.R;
import com.joye.hk6.StatusBarHelp;
import com.joye.hk6.ac.Hk6ReportAppCompatActivity;
import com.joye.hk6.internal.di.component.DaggerModular7Component;
import com.joye.hk6.internal.di.component.Modular7Component;
import com.joye.hk6.internal.di.modules.Hk6Module;
import com.joye.hk6.internal.di.modules.StatusbarActivityModule;
import com.joye.hk6.presenter.Modular7ActivityPresenter;
import com.joye.hk6.util.DateUtil;
import com.joye.hk6.vu.Modular7ActivityVu;

import javax.inject.Inject;

/**
 * Created by xiang on 16/9/29.
 */

public class Modular7Activity extends Hk6ReportAppCompatActivity<Modular7ActivityVu> implements HasComponent<Modular7Component> {
    Modular7Component mModularComponent;
    @Inject
    Modular7ActivityPresenter mRegionPresenter;
    @Inject
    StatusBarHelp statusBarHelp;
    public static final String TITLE = "模7走势预警";
    public static final int PICRESID = R.drawable.modular7;
    @Override
    protected void onBindVu() {
        super.onBindVu();
        initializeInjector();
        statusBarHelp.setStatusBarTintEnable(true, R.drawable.banner_bar_bg);
        vu.setErrorRetryListener(mRegionPresenter.getErrorRetryListener());
        vu.commonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRegionPresenter.setView(vu);
        mRegionPresenter.initalize(this);
    }
    public void initializeInjector() {
        mModularComponent = DaggerModular7Component.builder()
                .hk6ApplicationComponent(getApplicationComponent())
                .hk6Module(new Hk6Module(DateUtil.getDate()))
                .statusbarActivityModule(new StatusbarActivityModule(this)).build();
        mModularComponent.inject(this);
    }
    @Override
    public Modular7Component getComponent() {
        return mModularComponent;
    }

    @Override
    protected Class<Modular7ActivityVu> getVuClass() {
        return Modular7ActivityVu.class;
    }

    @Override
    public int getReportPic() {
        return PICRESID;
    }

    @Override
    public String getReportActivityTitle() {
        return TITLE;
    }


}
