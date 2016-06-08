package roff.startuparch.features.githubservice;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.OnClick;
import roff.startuparch.R;
import roff.startuparch.core.component.BaseActivity;

/**
 * Created by wuyongbo on 16-6-8.
 */
public class GithubServiceActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.githubservice);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.user_name)
    void onClickUserName() {
        Toast.makeText(this, "Click name", Toast.LENGTH_LONG).show();
    }
}