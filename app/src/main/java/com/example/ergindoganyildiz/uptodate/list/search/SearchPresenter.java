package com.example.ergindoganyildiz.uptodate.list.search;

import com.example.ergindoganyildiz.uptodate.list.data.TweetListInteractor;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by ergindoganyildiz on 6/26/17.
 * Does UI related work, passes data related work to TweetListInteractorImpl
 */

public class SearchPresenter implements SearchContract.Presenter, TweetListInteractor.TweetsLoadedCallback {

    private SearchContract.View searchListView;
    private TweetListInteractor tweetListInteractor;

    public SearchPresenter(SearchContract.View searchListView, TweetListInteractor tweetListInteractor) {
        this.searchListView = searchListView;
        this.tweetListInteractor = tweetListInteractor;

        searchListView.setPresenter(this);
    }

    @Override
    public void onResume() {}

    @Override
    public void onDestroy() {
        searchListView = null;
    }

    @Override
    public void onTweetsLoaded(List<Tweet> tweets) {
        if (searchListView != null) {
            if(tweets.isEmpty()){
                searchListView.showNoItemsText();
            }else{
                searchListView.hideNoItemsText();
            }

            searchListView.setItems(tweets);
            searchListView.hideProgress();
            searchListView.enableSearchButton();
        }
    }

    @Override
    public void onTweetsLoadedFailed(String message) {
        if (searchListView != null) {
            searchListView.hideNoItemsText();
            searchListView.hideProgress();
            searchListView.showToast(message);
            searchListView.enableSearchButton();
        }
    }

    @Override
    public void onSearchButtonClicked(String searchText) {
        if(searchListView != null){
            searchListView.disableSearchButton();
            searchListView.showProgress();
            searchListView.hideNoItemsText();
            searchListView.hideKeyboard();
        }
        tweetListInteractor.searchTweets(searchText, this);
    }

    @Override
    public void onItemClick(ListItem item) {
        searchListView.startDetailActivity(item);
    }

}
