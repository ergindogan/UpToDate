package com.example.ergindoganyildiz.uptodate.list.base.fragment;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.base.BaseView;

/**
 * Created by ergindoganyildiz on 7/6/17.
 */

public interface BaseFragmentView<T extends BasePresenter> extends BaseView<T> {

    void onTokenLoaded();

}
