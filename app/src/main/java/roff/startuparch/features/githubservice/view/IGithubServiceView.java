package roff.startuparch.features.githubservice.view;

import java.util.List;

import roff.startuparch.features.githubservice.beans.GithubUserInfo;
import roff.startuparch.features.githubservice.beans.GithubUserRepo;

/**
 * Created by wuyongbo on 16-6-8.
 */
public interface IGithubServiceView {
    void onUserInfoSuccess(GithubUserInfo githubUserInfo);
    void onUserInfoFail(String errorMsg);

    void onUserRepoSuccess(List<GithubUserRepo> githubUserRepoList);
    void onUserRepoFail(String errorMsg);
}
