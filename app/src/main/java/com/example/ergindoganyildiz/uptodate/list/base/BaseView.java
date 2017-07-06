package com.example.ergindoganyildiz.uptodate.list.base;

import java.util.List;

/**
 * Created by ergindoganyildiz on 6/28/17.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

    void setItems(List<?> items);

    void showNoItemsText();

    void hideNoItemsText();
}
