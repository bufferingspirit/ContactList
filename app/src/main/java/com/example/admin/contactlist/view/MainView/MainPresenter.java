package com.example.admin.contactlist.view.MainView;


import android.util.Log;

import com.example.admin.contactlist.data.DataCallback;
import com.example.admin.contactlist.data.DataHelper;
import com.example.admin.contactlist.model.Contact.Contact;
import com.example.admin.contactlist.model.StoredContact.StoredContact;

import java.util.ArrayList;

/**
 * Created by Admin on 10/11/2017.
 */

public class MainPresenter implements MainContract.MainPresenter, DataCallback{

    public static final String TAG = "MainPresenter";
    MainContract.MainView view;
    DataHelper dataHelper;
    Contact contact;

    @Override
    public void attachView(MainContract.MainView view) {
        this.view = view;
        dataHelper = new DataHelper(this, view.GetContext());
        this.contact = null;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void ParseContactNetwork(Contact contact) {
        this.contact = contact;
        System.out.println("API CALL: " + contact.getResults().get(0).getName().getFirst());
        view.UpdateView(contact);
    }

    @Override
    public void ParseContactCache(ArrayList<StoredContact> contact) {
        Log.d(TAG, "ParseContactCache: " + "ERROR: Improper Callback");
    }

    @Override
    public void dataError(String s) {
        //TODO deal with raw messages
        view.showError(s);
    }

    @Override
    public void LoadContactView() {
        dataHelper.GetNetworkContact();
    }

    @Override
    public void SaveContact() {
        if(contact != null) {
            dataHelper.SaveContact(contact);
        } else {
            view.showError("Contact Is Missing");
        }
    }


}
