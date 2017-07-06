package com.example.ergindoganyildiz.uptodate.list.fragments.tweetdetail;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public interface TweetDetailContract {

    interface View{
        void setPresenter(BasePresenter presenter);

        void bindValuesIntoComponents();
    }

    interface Presenter extends BasePresenter{

    }
}
