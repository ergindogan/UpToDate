package com.example.ergindoganyildiz.uptodate.list.fragments.userstweets;

import com.example.ergindoganyildiz.uptodate.list.base.fragment.BasePresenterImpl;
import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractor;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class UsersTweetsPresenter extends BasePresenterImpl<UsersTweetsContract.FragmentView> implements UsersTweetsContract.Presenter,
        TweetDetailInteractor.TweetsLoadedCallback{

    public UsersTweetsPresenter(UsersTweetsContract.FragmentView usersTweetsView, TweetDetailInteractor tweetDetailInteractor){
        view = usersTweetsView;
        interactor = tweetDetailInteractor;

        usersTweetsView.setPresenter(this);
    }

    @Override
    public void getUsersTweets(String userId, int count) {
        if(view != null){
            view.showProgress();
            view.hideNoItemsText();
        }
        interactor.getUsersTweets(userId, count, this);
    }

    @Override
    public void onTweetsLoaded(List<Tweet> tweets) {
        if (view != null) {
            if(tweets.isEmpty()){
                view.showNoItemsText();
            }else{
                view.hideNoItemsText();
            }

            view.setItems(tweets);
            view.hideProgress();
        }
    }

    @Override
    public void onTweetsLoadedFailed(String message) {
        if (view != null) {
            view.showNoItemsText();
            view.hideProgress();
        }
    }
}
