package com.joye.hk6.internal.di.component;

import com.joye.basepresentation.internal.di.PerActivity;
import com.joye.hk6.internal.di.modules.MainModule;
import com.joye.hk6.view.MainActivity;

import dagger.Component;

/**
 * Created by Wx on 2016/9/26.
 * QQ:304622254;
 * remark:
 */
@PerActivity
@Component(dependencies = Hk6ApplicationComponent.class,modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
