package roff.startuparch.core.di.component;

import android.app.Activity;

import dagger.Component;
import roff.startuparch.core.di.annotation.PerActivity;
import roff.startuparch.core.di.module.ActivityModule;

/**
 * Created by wuyongbo on 16-3-22.
 *
 * Activity级别的Component必须扩展这个Component
 *
 * 说明1:
 * parent component 不需要inject()方法
 *
 * 说明2:
 * 必须限定 {@link javax.inject.Scope}
 * 如果不加 {@link PerActivity} 会有错误:roff.startuparch.core.di.component.ActivityComponent (unscoped) may not reference scoped bindings:
 */
@PerActivity
@Component(
        dependencies = AppModule.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {
    Activity getActivity();
}
