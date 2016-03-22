package roff.startuparch.presentation.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import roff.startuparch.presentation.core.BaseActivity;

/**
 * Created by wuyongbo on 16-3-22.
 * Component的作用是连接"实例对象提供者(Module)"和"使用者(inject的调用者)"
 */
@Singleton //限制该Component跟Application生命周期一致或unscoped bindings ???
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * <---供使用者里通过@Inject来获取
     *
     * 通过inject方法来绑定生命周期
     */
    void inject(BaseActivity activity);

    /**
     * <---跟Module的@Provides方法对应,名字可以不一样,返回值类型一致即可
     *
     * 跟 {@link AppModule_GetApplicationContextFactory}对应, 在使用者里通过@Inject来获取
     */
    Context context();
}
