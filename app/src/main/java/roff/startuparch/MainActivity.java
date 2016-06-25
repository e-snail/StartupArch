package roff.startuparch;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import roff.startuparch.core.component.BaseActivity;
import roff.startuparch.features.doubanservice.DoubanServiceActivity;
import roff.startuparch.features.githubservice.GithubServiceActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.githubservice)
    void onClickGithubService() {
        Intent intent = new Intent(this, GithubServiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
