package com.joye.hk6.presenter;

import android.view.View;

import com.joye.basedomain.interactor.DefaultSubscriber;
import com.joye.basedomain.interactor.UseCase;
import com.joye.basepresentation.internal.di.PerActivity;
import com.joye.hk6.mvp.Presenter;
import com.joye.hk6.vu.SizeFragmentVu;
import com.joye.hk6domain.vo.SizeVo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/22.
 * 304622254@qq.com
 * Remeark:大小概率
 */
@PerActivity
public class SizeFrgmentPresenter implements Presenter<SizeFragmentVu> {


    private final UseCase mHk6DataUseCase;

    private SizeFragmentVu vu;


    @Inject
    public SizeFrgmentPresenter(@Named("GetSizeUseCase")UseCase getHk6DataUseCase) {
        this.mHk6DataUseCase = getHk6DataUseCase;
    }


    @Override
    public void setView(SizeFragmentVu chineseZodiacProbilityFragmentVu) {
        this.vu=chineseZodiacProbilityFragmentVu;
    }

    public void initalize( ){
        loadHk6Data();
    }
    private void getHk6ListData(){
            mHk6DataUseCase.execute(new Hk6ListDataSubscriber());
    }
    private void loadHk6Data() {
        vu.showLoading();
        getHk6ListData();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public final class Hk6ListDataSubscriber extends DefaultSubscriber<List<SizeVo>>{
        @Override
        public void onCompleted() {
            SizeFrgmentPresenter.this.vu.showContent();
        }

        @Override
        public void onError(Throwable e) {
            vu.showError(e);
        }

        @Override
        public void onNext(List<SizeVo> hk6UiDatas) {
            vu.onNext(hk6UiDatas);
        }
    }

    @Override
    public void destroy() {
        mHk6DataUseCase.unsubscribe();
    }

    public View.OnClickListener getErrorRetryListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHk6ListData();
            }
        };
    }
}
