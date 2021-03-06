package com.joye.hk6.view;

import android.support.v7.widget.LinearLayoutManager;

import com.joye.basepresentation.internal.di.HasComponent;
import com.joye.hk6.R;
import com.joye.hk6.StatusBarHelp;
import com.joye.hk6.ac.Hk6ReportAppCompatActivity;
import com.joye.hk6.internal.di.component.DaggerMantissaComponent;
import com.joye.hk6.internal.di.component.MantissaComponent;
import com.joye.hk6.internal.di.modules.Hk6Module;
import com.joye.hk6.internal.di.modules.StatusbarActivityModule;
import com.joye.hk6.presenter.MantissaActivityPresenter;
import com.joye.hk6.util.DateUtil;
import com.joye.hk6.vu.MantissaActivityVu;

import javax.inject.Inject;

/**
 * Created by xiang on 16/9/29.
 */

public class MantissaActivity extends Hk6ReportAppCompatActivity<MantissaActivityVu> implements HasComponent<MantissaComponent> {
    MantissaComponent mMantissaComponent;
    @Inject
    MantissaActivityPresenter mMantissaActivityPresenter;
    @Inject
    StatusBarHelp statusBarHelp;
    public static final String TITLE = "尾数走势预警";
    public static final int PICRESID = R.drawable.mantissa;
    @Override
    protected void onBindVu() {
        super.onBindVu();
        initializeInjector();
        statusBarHelp.setStatusBarTintEnable(true, R.drawable.banner_bar_bg);
        vu.setErrorRetryListener(mMantissaActivityPresenter.getErrorRetryListener());
        vu.commonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMantissaActivityPresenter.setView(vu);
        mMantissaActivityPresenter.initalize(this);
    }
    public void initializeInjector() {
        mMantissaComponent = DaggerMantissaComponent.builder()
                .hk6ApplicationComponent(getApplicationComponent())
                .hk6Module(new Hk6Module(DateUtil.getDate()))
                .statusbarActivityModule(new StatusbarActivityModule(this)).build();
        mMantissaComponent.inject(this);
    }
    @Override
    public MantissaComponent getComponent() {
        return mMantissaComponent;
    }

    @Override
    protected Class<MantissaActivityVu> getVuClass() {
        return MantissaActivityVu.class;
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
