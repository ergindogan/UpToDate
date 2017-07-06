package com.example.ergindoganyildiz.uptodate.list.fragments.tweetdetail;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class TweetDetailPresenter implements TweetDetailContract.Presenter {

    private TweetDetailContract.View tweetDetailView;

    public TweetDetailPresenter(TweetDetailContract.View tweetDetailView){
        this.tweetDetailView = tweetDetailView;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        tweetDetailView = null;
    }
}
