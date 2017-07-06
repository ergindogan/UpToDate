package com.example.ergindoganyildiz.uptodate.list.fragments.userstweets;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.base.fragment.BaseFragmentView;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public interface UsersTweetsContract {

    interface FragmentView extends BaseFragmentView<Presenter> {
    }

    interface Presenter extends BasePresenter{
        void getUsersTweets(String userId, int count);
    }
}
