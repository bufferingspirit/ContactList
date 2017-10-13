package com.example.admin.contactlist.view.ContactView;

import android.content.Context;

import com.example.admin.contactlist.BasePresenter;
import com.example.admin.contactlist.BaseView;
import com.example.admin.contactlist.model.StoredContact.StoredContact;

import java.util.ArrayList;

/**
 * Created by Admin on 10/11/2017.
 */

public interface ContactContract {

    interface ContactView extends BaseView{
        void UpdateView(ArrayList<StoredContact> c);
        Context GetContext();
    }

    interface ContactPresenter extends BasePresenter<ContactContract.ContactView> {
        void LoadContactList();
    }
}
