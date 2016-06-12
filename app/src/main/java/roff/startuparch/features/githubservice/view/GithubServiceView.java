package roff.startuparch.features.githubservice.view;

import android.util.Log;

import java.util.List;

import roff.startuparch.features.githubservice.beans.GithubUserInfo;
import roff.startuparch.features.githubservice.beans.GithubUserRepo;

/**
 * Created by wuyongbo on 16-6-8.
 */
public class GithubServiceView implements IGithubServiceView {

    static final String TAG = GithubServiceView.class.getSimpleName();

    @Override
    public void onUserInfoSuccess(GithubUserInfo githubUserInfo) {
        Log.d(TAG, "onUserInfoSuccess userName " + githubUserInfo.getName());
    }

    @Override
    public void onUserInfoFail(String errorMsg) {
        Log.d(TAG, "onUserInfoFail with errorMsg " + errorMsg);
    }

    @Override
    public void onUserRepoSuccess(List<GithubUserRepo> githubUserRepoList) {
        Log.d(TAG, "onUserRepoSuccess user repo count " + githubUserRepoList.size());
    }

    @Override
    public void onUserRepoFail(String errorMsg) {
        Log.d(TAG, "onUserRepoFail with errorMsg " + errorMsg);
    }
}
