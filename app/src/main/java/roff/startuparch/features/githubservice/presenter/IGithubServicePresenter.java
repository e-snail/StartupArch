package roff.startuparch.features.githubservice.presenter;

import roff.startuparch.features.githubservice.view.IGithubServiceView;

/**
 * Created by wuyongbo on 16-6-8.
 */
public interface IGithubServicePresenter {
    void setIGithubServiceView(IGithubServiceView githubServiceView);
    void getUserRepos(String user);
    void getUserInfo(String user);
}
