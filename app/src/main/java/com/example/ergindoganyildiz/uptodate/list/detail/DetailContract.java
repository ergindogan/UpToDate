package com.example.ergindoganyildiz.uptodate.list.detail;

import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public interface DetailContract {

    interface View{
        void setPresenter(BasePresenter presenter);
    }

    interface Presenter extends BasePresenter{

    }
}
