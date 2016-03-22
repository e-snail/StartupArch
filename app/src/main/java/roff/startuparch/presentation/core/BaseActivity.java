package roff.startuparch.presentation.core;

import android.app.Activity;
import android.os.Bundle;

import roff.startuparch.presentation.di.AppComponent;
import roff.startuparch.presentation.di.AppComponentProvider;

/**
 * Created by wuyongbo on 16-3-22.
 *
 * 所有Activity的基类, 提供常用的方法
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    //提供最顶级的Component
    protected AppComponent getApplicationComponent() {
        return ((AppComponentProvider)getApplication()).getAppComponent();
    }
}
