package com.joye.basepresentation.internal.di.components;

/**
 * Created by xiang on 16/9/21.
 */

import android.content.Context;

import com.joye.basedomain.executor.PostExecutionThread;
import com.joye.basedomain.executor.ThreadExecutor;
import com.joye.basepresentation.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 Components从根本上来说就是一个注入器，
 也可以说是@Inject和@Module的桥梁，
 它的主要作用就是连接这两个部分。
 Components可以提供所有定义了的类型的实例，
 比如：我们必须用@Component注解一个接口然后列出所有的@Modules组成该组件，
 如果缺失了任何一块都会在编译的时候报错。
 所有的组件都可以通过它的modules知道依赖的范围。
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

}
