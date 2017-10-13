package com.example.admin.contactlist.data;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.admin.contactlist.model.Contact.Contact;
import com.example.admin.contactlist.model.Contact.Result;
import com.example.admin.contactlist.model.StoredContact.StoredContact;


import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 10/11/2017.
 */

public class DataHelper {

    DataCallback presenter;
    APIHelper service;
    DatabaseHelper db;
    public DataHelper(DataCallback presenter, Context context){
        service = new APIHelper();
        db = new DatabaseHelper(context);
        this.presenter = presenter;
    }

    public void GetNetworkContact(){
        service.getContactApi()
                .getContactObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Contact>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {

                       }

                       @Override
                       public void onNext(@NonNull Contact contact) {
                           System.out.println("API CALL: " + contact.getResults().get(0).getName().getTitle());
                           presenter.ParseContactNetwork(contact);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                           presenter.dataError(e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }
                });

    }

    public void GetContactCache(){
        ArrayList<StoredContact> contacts = db.GetContactList();
        presenter.ParseContactCache(contacts);
    }

    public void SaveContact(Contact contact){
        Result result = contact.getResults().get(0);
        StoredContact foo = new StoredContact();
        //TODO Cache Thumbnail
        //TODO Cache Large

        foo.setFirstName(result.getName().getFirst());
        foo.setLastName(result.getName().getLast());
        foo.setEmail(result.getEmail());
        foo.setStreet(result.getLocation().getStreet());
        foo.setCity(result.getLocation().getCity());
        foo.setState(result.getLocation().getState());
        foo.setPostcode(result.getLocation().getPostcode().toString());
        db.SaveNewContact(foo);
    }

}
