package com.example.admin.contactlist.view.MainView;

import com.example.admin.contactlist.BasePresenter;
import com.example.admin.contactlist.BaseView;

/**
 * Created by Admin on 10/11/2017.
 */

public interface MainContract {

    interface MainView extends BaseView{

    }

    interface MainPresenter extends BasePresenter<MainContract.MainView>{

    }
}
