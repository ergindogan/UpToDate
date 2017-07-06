package com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.base.fragment.BaseFragmentView;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public interface UsersFollowersContract {

    interface FragmentView extends BaseFragmentView<Presenter> {
    }

    interface Presenter extends BasePresenter{
        void getUsersFollowers(String userId, int count);
    }
}
