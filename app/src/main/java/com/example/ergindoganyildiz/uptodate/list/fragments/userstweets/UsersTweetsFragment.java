package com.example.ergindoganyildiz.uptodate.list.fragments.userstweets;

import com.example.ergindoganyildiz.uptodate.list.base.fragment.BaseFragment;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class UsersTweetsFragment extends BaseFragment<UsersTweetsContract.Presenter> implements UsersTweetsContract.FragmentView {

    public static UsersTweetsFragment newInstance(String userId, int count) {
        UsersTweetsFragment fragment = new UsersTweetsFragment();
        fragment.userId = userId;
        fragment.count = count;
        return fragment;
    }
}
