package com.example.admin.contactlist.view.ContactView;

import com.example.admin.contactlist.BasePresenter;
import com.example.admin.contactlist.BaseView;

/**
 * Created by Admin on 10/11/2017.
 */

public interface ContactContract {

    interface ContactView extends BaseView{

    }

    interface ContactPresenter extends BasePresenter<ContactContract.ContactView> {

    }
}
