package com.example.ergindoganyildiz.uptodate.list.search;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.base.BaseView;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;

/**
 * Created by ergindoganyildiz on 6/28/17.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter>{
        void startDetailActivity(ListItem item);

        void showToast(String message);

        void disableSearchButton();

        void enableSearchButton();

        void hideKeyboard();
    }

    interface Presenter extends BasePresenter{
        void onSearchButtonClicked(String searchText);

        void onItemClick(ListItem item);
    }
}
