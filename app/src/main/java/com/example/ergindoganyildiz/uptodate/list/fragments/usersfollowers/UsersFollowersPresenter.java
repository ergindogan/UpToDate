package com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers;

import com.example.ergindoganyildiz.uptodate.list.base.fragment.BasePresenterImpl;
import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractor;
import com.example.ergindoganyildiz.uptodate.list.model.FollwersListResponse;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class UsersFollowersPresenter extends BasePresenterImpl<UsersFollowersContract.FragmentView> implements UsersFollowersContract.Presenter,
        TweetDetailInteractor.FollowersLoadedCallback{

    public UsersFollowersPresenter(UsersFollowersContract.FragmentView usersFollowersView,
                                   TweetDetailInteractor tweetDetailInteractor){
        view = usersFollowersView;
        interactor = tweetDetailInteractor;

        view.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getUsersFollowers(String userId, int count) {
        if(view != null){
            view.showProgress();
            view.hideNoItemsText();
        }

        compositeDisposable.add(interactor.getFollowersList(userId, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(follwersListResponse -> onFollowersLoaded(follwersListResponse),
                        throwable -> onFollowersLoadedFailed()));
    }

    @Override
    public void onFollowersLoaded(FollwersListResponse response) {
        List<User> followers = new ArrayList<User>();
        if (view != null) {
            if(response != null){
                followers = response.getUsers();
                if(followers.isEmpty()){
                    view.showNoItemsText();
                }else{
                    view.hideNoItemsText();
                }
            }

            view.setItems(followers);
            view.hideProgress();
        }
    }

    @Override
    public void onFollowersLoadedFailed() {
        if (view != null) {
            view.showNoItemsText();
            view.hideProgress();
        }
    }
}
