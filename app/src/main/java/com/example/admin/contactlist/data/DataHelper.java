package com.example.admin.contactlist.data;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.admin.contactlist.model.Contact;


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
    ContactAPIHelper service;
    public DataHelper(DataCallback presenter){
        service = new ContactAPIHelper();
        this.presenter = presenter;
    };

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
        ArrayList<Contact> contacts = null;

        presenter.ParseContactCache(contacts);
    }

    public void SaveContact(Contact contact){
        //TODO cache full image
        //TODO cache thumbnail
    }

    public void GetThumbnail(){};

    public void GetLargeImage(){};

    private boolean contact_is_cached(){
        return false;
    };
}
