package com.example.admin.contactlist.view.ContactView;

import android.util.Log;

import com.example.admin.contactlist.data.DataCallback;
import com.example.admin.contactlist.data.DataHelper;
import com.example.admin.contactlist.model.Contact;

import java.util.ArrayList;

/**
 * Created by Admin on 10/11/2017.
 */

public class ContactPresenter implements ContactContract.ContactPresenter, DataCallback {

    public static final String TAG = "ContactPresenter";

    ContactContract.ContactView view;

    DataHelper dataHelper;

    @Override
    public void attachView(ContactContract.ContactView view) {
        this.view = view;
        dataHelper = new DataHelper(this);
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void ParseContactNetwork(Contact contact) {
        Log.d(TAG, "ParseContactNetwork: " + " ERROR: Improper Callback");
    }

    @Override
    public void ParseContactCache(ArrayList<Contact> contacts) {
        view.UpdateView(contacts);
    }

    @Override
    public void dataError(String s) {
        view.showError(s);
    }

    @Override
    public void LoadContactList() {
        dataHelper.GetContactCache();
    }
}
