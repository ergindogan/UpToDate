package com.example.ergindoganyildiz.uptodate.list.base.fragment;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractor;
import com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers.UsersFollowersContract;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ergindoganyildiz on 7/6/17.
 */

public abstract class BasePresenterImpl<T extends BaseFragmentView> implements BasePresenter, TweetDetailInteractor.TokenCallback {

    public T view;
    public TweetDetailInteractor interactor;

    public CompositeDisposable compositeDisposable;

    @Override
    public void onResume() {
        interactor.getToken(this);
    }

    @Override
    public void onDestroy() {
        if(view instanceof UsersFollowersContract.FragmentView){
            compositeDisposable.dispose();
        }
        view = null;
    }

    @Override
    public void tokenRetrievedSuccess() {
        view.onTokenLoaded();
    }

    @Override
    public void tokenRetrievedFail() {
        if (view != null) {
            view.showNoItemsText();
            view.hideProgress();
        }
    }
}
