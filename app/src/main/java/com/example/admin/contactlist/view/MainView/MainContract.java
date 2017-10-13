package com.example.admin.contactlist.view.MainView;

import android.content.Context;

import com.example.admin.contactlist.BasePresenter;
import com.example.admin.contactlist.BaseView;
import com.example.admin.contactlist.model.Contact.Contact;

/**
 * Created by Admin on 10/11/2017.
 */

public interface MainContract {

    interface MainView extends BaseView{
        void UpdateView(Contact contact);
        Context GetContext();
    }

    interface MainPresenter extends BasePresenter<MainContract.MainView>{
        void LoadContactView();
        void SaveContact();
    }
}
