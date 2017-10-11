package com.example.admin.contactlist;

/**
 * Created by Admin on 10/11/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();
}
