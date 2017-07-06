package com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers;

import com.example.ergindoganyildiz.uptodate.list.base.fragment.BaseFragment;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class UsersFollowersFragment extends BaseFragment<UsersFollowersContract.Presenter> implements UsersFollowersContract.FragmentView {

    public static UsersFollowersFragment newInstance(String userId, int count) {
        UsersFollowersFragment fragment = new UsersFollowersFragment();
        fragment.userId = userId;
        fragment.count = count;
        return fragment;
    }

}
