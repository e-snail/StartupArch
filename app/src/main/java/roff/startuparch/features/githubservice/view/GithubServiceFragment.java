package roff.startuparch.features.githubservice.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import roff.startuparch.R;
import roff.startuparch.features.githubservice.beans.GithubUserInfo;
import roff.startuparch.features.githubservice.beans.GithubUserRepo;
import roff.startuparch.features.githubservice.presenter.IGithubServicePresenter;

/**
 * Created by wuyongbo on 16-6-12.
 */
public class GithubServiceFragment extends Fragment implements IGithubServiceView {

    IGithubServicePresenter presenter;

    public GithubServiceFragment() {
    }

    @BindView(R.id.clickme)
    Button clickmeButton;

    @OnClick(R.id.clickme)
    void onClickMe(View view) {
        presenter.getUserInfo("e-snail");
        presenter.getUserRepos("e-snail");
    }

    @BindView(R.id.login)
    TextView loginTextView;

    @OnClick(R.id.login)
    void onClickLogin(View view) {
        Toast.makeText(context, "Login onclick", Toast.LENGTH_LONG).show();
    }

    @BindView(R.id.user_ID)
    TextView userIDTextView;

    @OnClick(R.id.user_ID)
    void onClickUser(View view) {
        Toast.makeText(context, "UserID onclick", Toast.LENGTH_LONG).show();
    }

    @BindView(R.id.location)
    TextView locationTextView;

    @OnClick(R.id.location)
    void onClickLocation(View view) {
        Toast.makeText(context, "Location onclick", Toast.LENGTH_LONG).show();
    }

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.githubservice_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setPresenter(@NonNull IGithubServicePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onUserInfoSuccess(GithubUserInfo githubUserInfo) {
        loginTextView.setText("LoginName:" + githubUserInfo.getLogin());
        userIDTextView.setText("UserID:" + githubUserInfo.getId());
        locationTextView.setText("Location:" + githubUserInfo.getLocation());
    }

    @Override
    public void onUserInfoFail(String errorMsg) {

    }

    @Override
    public void onUserRepoSuccess(List<GithubUserRepo> githubUserRepoList) {
        Toast.makeText(context, "onUserRepoSuccess repo size = " + githubUserRepoList.size(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserRepoFail(String errorMsg) {

    }
}
