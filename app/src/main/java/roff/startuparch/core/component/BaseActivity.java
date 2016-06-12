package roff.startuparch.core.component;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import roff.startuparch.core.di.component.ApplicationComponent;
import roff.startuparch.core.di.component.ApplicationComponentProvider;

/**
 * Created by wuyongbo on 16-3-22.
 *
 * 所有Activity的基类, 提供常用的方法
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ButterKnife.bind(this);
    }

    /**
     * 提供Application级的Component
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((ApplicationComponentProvider)getApplication()).getApplicationComponent();
    }
}
