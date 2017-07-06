package com.example.ergindoganyildiz.uptodate.list.detail;

import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractor;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class DetailPresenter implements DetailContract.Presenter{

    private DetailContract.View fragmentHolderView;
    private TweetDetailInteractor tweetDetailInteractor;

    public DetailPresenter(DetailContract.View fragmentHolderView, TweetDetailInteractor tweetDetailInteractor){
        this.fragmentHolderView = fragmentHolderView;
        this.tweetDetailInteractor = tweetDetailInteractor;

        fragmentHolderView.setPresenter(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        fragmentHolderView = null;
    }

}
